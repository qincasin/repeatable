package com.qjx.sample.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.cglib.beans.BeanMap;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * BeanHelper
 */
@Slf4j
public final class BeanHelper {
    private static final String DATAS_JSON = "{\"datas\":%s}";


    /**
     * Hide Utility Class Constructor
     */
    private BeanHelper() {
    }

    /**
     * copyProperties
     *
     * @param source Object
     * @param target Object
     */
    public static void copyProperties(Object source, Object target) {
        if (source != null && target != null) {
            String[] properties = getCopyFields(source.getClass(), target.getClass());
            copyProperties(source, target, properties);
        }
    }

    /**
     * copyProperties
     *
     * @param source     Object
     * @param target     Object
     * @param properties String[]
     */
    public static void copyProperties(Object source, Object target, String[] properties) {
        if (source != null && target != null) {
            BeanWrapper src = new BeanWrapperImpl(source);
            BeanWrapper trg = new BeanWrapperImpl(target);
            Object value;
            for (String propertyName : properties) {
                try {
                    value = src.getPropertyValue(propertyName);
                    if (value instanceof String) {
                        value = ((String) value).trim();
                    }
                    trg.setPropertyValue(propertyName, value);
                } catch (BeansException e) {
                    // do nothing
                }
            }
        }
    }

    /**
     * copyPropertiesNoNull
     *
     * @param source Object
     * @param target Object
     */
    public static void copyPropertiesNoNull(Object source, Object target) {
        if (source != null && target != null) {
            String[] properties = getCopyFields(source.getClass(), target.getClass());
            copyPropertiesNoNull(source, target, properties);
        }
    }

    /**
     * copyPropertiesNoNull
     *
     * @param source     Object
     * @param target     Object
     * @param properties String[]
     */
    public static void copyPropertiesNoNull(Object source, Object target, String[] properties) {
        if (source != null && target != null) {
            BeanWrapper src = new BeanWrapperImpl(source);
            BeanWrapper trg = new BeanWrapperImpl(target);
            Object value;
            for (String propertyName : properties) {
                try {
                    value = src.getPropertyValue(propertyName);
                    if (value instanceof String) {
                        value = ((String) value).trim();
                    } else if (value == null && src.getPropertyType(propertyName) == String.class) {
                        value = "";
                    }
                    trg.setPropertyValue(propertyName, value);
                } catch (BeansException e) {
                    // do nothing
                }
            }
        }
    }

    /**
     * getCopyFields
     *
     * @param source Class
     * @param target Class
     * @return String[]
     */
    public static String[] getCopyFields(Class<?> source, Class<?> target) {
        String[] properties;
        Field[] sourceDeclaredFields = getDeclaredNotSyntheticFields(source);
        Field[] targetDeclaredFields = getDeclaredNotSyntheticFields(target);
        if (sourceDeclaredFields.length > targetDeclaredFields.length) {
            properties = getInverseFields(target, null);
        } else {
            properties = getInverseFields(source, null);
        }
        return properties;
    }

    /**
     * getInverseFields
     *
     * @param clz            Class
     * @param excludedFields String[]
     * @return String[]
     */
    public static String[] getInverseFields(Class<?> clz, String[] excludedFields) {
        Field[] declaredFields = getDeclaredNotSyntheticFields(clz);
        List<String> fieldList = new ArrayList<>();
        if (excludedFields == null || excludedFields.length == 0) {
            for (Field f : declaredFields) {
                fieldList.add(f.getName());
            }
        } else {
            List<String> excludedlist = Arrays.asList(excludedFields);
            String fieldName;
            for (Field f : declaredFields) {
                fieldName = f.getName();
                if (!excludedlist.contains(fieldName)) {
                    fieldList.add(fieldName);
                }
            }
        }
        return fieldList.toArray(new String[0]);
    }

    /**
     * getFieldName
     *
     * @param propertyName String
     * @param clz          Class
     * @return String
     */
    public static String getFieldName(String propertyName, Class<?> clz) {
        if (propertyName == null) {
            return null;
        }
        Field[] declaredFields = getDeclaredNotSyntheticFields(clz);
        String fieldName;
        for (Field f : declaredFields) {
            fieldName = f.getName();
            if (fieldName.equalsIgnoreCase(propertyName)) {
                return fieldName;
            }
        }
        return null;
    }

    /**
     * getGetMethod
     *
     * @param propertyName String
     * @param clz          Class
     * @return Method
     */
    public static Method getGetMethod(String propertyName, Class<?> clz) {
        if (propertyName == null) {
            return null;
        }
        Method[] declaredMethods = clz.getMethods();
        for (Method d : declaredMethods) {
            if (d.getName().equalsIgnoreCase("get" + propertyName) || d.getName()
                    .equalsIgnoreCase("is" + propertyName)) {
                return d;
            }
        }
        return null;
    }

    /**
     * getIgnoreProperties
     *
     * @param request HttpServletRequest
     * @param clz     Class
     * @return String[]
     */
    public static String[] getProperties(HttpServletRequest request, Class<?> clz) {
        Map<String, Object> datas = getEntity(request);
        return getProperties(datas, clz);
    }

    /**
     * 获取null 值的Perpertie Names
     * @param source
     * @return String[]
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 获取null or 空值的Perpertie Names
     * @param source
     * @return String[]
     */
    public static String[] getNullOrEmptyPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            } else if (srcValue instanceof String){
                if (StringUtils.isBlank(String.valueOf(srcValue))){
                    emptyNames.add(pd.getName());
                }
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * getRequestHeaders
     *
     * @param request HttpServletRequest
     * @return Map<String, String>
     * @throws IOException IOException
     */
    public static Map<String, String> getRequestHeaders(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>(10);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * getRequestBody
     *
     * @param request HttpServletRequest
     * @return String
     * @throws IOException IOException
     */
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        String src;
        try {
            src = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
        } catch (IllegalStateException ex) {
            src = IOUtils.toString(request.getReader());
            log.error("BeanHelper.getRequestBody() error:", ex);
        }
        return src;
    }

    /**
     * bean2Json
     *
     * @param bean Object
     * @return String
     */
    public static String bean2Json(Object bean) {
        String result = "";
        try {
            result = pBean2Json(bean, JsonInclude.Include.ALWAYS);
        } catch (IOException e) {
            log.error("pBean2Json() error:", e);
        }
        return result;
    }

    /**
     * bean2Json with include type
     *
     * @param bean Object
     * @return String
     */
    public static String bean2Json(Object bean, JsonInclude.Include includeType) {
        String result = "";
        try {
            result = pBean2Json(bean, includeType);
        } catch (IOException e) {
            log.error("pBean2Json() error:", e);
        }
        return result;
    }

    /**
     * json2Bean
     *
     * @param json String
     * @param clz  Class
     * @param <T>  bean class
     * @return bean class
     */
    public static <T> T json2Bean(String json, Class<T> clz) {
        T bean = null;
        if (StringUtils.isNotBlank(json)) {
            try {
                bean = pJson2Bean(json, clz);
            } catch (Exception e) {
                log.error("pJson2Bean() error:", e);
            }
        }
        return bean;
    }

    /**
     * json2Bean
     * 泛型类
     *
     * @param json                   String
     * @param clz                    Class
     * @param <T>                    bean class
     * @param parameterizedTypeClass Class[]
     * @return bean class
     */
    public static <T> T json2Bean(String json, Class<?> clz, Class<?>... parameterizedTypeClass) {
        T bean = null;
        if (StringUtils.isNotBlank(json)) {
            try {
                bean = pJson2Bean(json, clz, parameterizedTypeClass);
            } catch (Exception e) {
                log.error("pJson2Bean() error:", e);
            }
        }
        return bean;
    }

    public static String bean2csv(Object bean) {
        StringBuilder buffer = new StringBuilder(1024 * 8);
        Field[] declaredFields = getDeclaredNotSyntheticFields(bean.getClass());
        Object value;
        BeanWrapper src = new BeanWrapperImpl(bean);
        for (Field f : declaredFields) {
            try {
                value = src.getPropertyValue(f.getName());
                buffer.append("\"").append(value).append("\",");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        String result = "";
        if (buffer.length() > 0) {
            result = buffer.substring(0, buffer.length() - 1);
        }
        return result;
    }

    /**
     * bean2csv
     *
     * @param bean           Object
     * @param excludedFields String[]
     * @return String
     */
    public static String bean2csv(Object bean, String[] excludedFields) {
        StringBuilder buffer = new StringBuilder(1024 * 8);
        String[] properties = getInverseFields(bean.getClass(), excludedFields);
        Object value;
        BeanWrapper src = new BeanWrapperImpl(bean);
        for (String propertyName : properties) {
            try {
                value = src.getPropertyValue(propertyName);
                buffer.append("\"").append(value).append("\",");
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        String result = "";
        if (buffer.length() > 0) {
            result = buffer.substring(0, buffer.length() - 1);
        }
        return result;
    }

    /**
     * convertToJsonData
     *
     * @param listOfObject List
     * @return String
     */
    public static String convertToJsonData(List<?> listOfObject) {
        String resultJson = bean2Json(listOfObject);
        return String.format(DATAS_JSON, resultJson);
    }

    /**
     * mapToBean
     *
     * @param map   Map
     * @param clazz Class
     * @param <T>   Bean
     * @return T
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        T bean = newInstance(clazz);
        BeanMap.create(bean).putAll(map);
        return bean;
    }

    /**
     * beanToMap
     *
     * @param bean Object
     * @return Map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> beanToMap(Object bean) {
        return null == bean ? null : BeanMap.create(bean);
    }


    /**
     * copyModifiedProperties
     *
     * @param source Object
     * @param target Object
     */
    public static String[] copyModifiedProperties(Object source, Object target) {
        String[] properties = getModifiedProperties(source, target);
        copyProperties(source, target, properties);
        return properties;
    }

    /**
     * copyModifiedProperties
     *
     * @param source Object
     * @param target Object
     */
    public static <T> T copyModifiedPropertiesToTarget(Object source, T target) {
        String[] properties = getModifiedProperties(source, target);
        copyProperties(source, target, properties);

        String[] inverseFields = getInverseFields(target.getClass(), properties);
        BeanWrapper trg = new BeanWrapperImpl(target);
        for (String propertyName : inverseFields) {
            try {
                trg.setPropertyValue(propertyName, null);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
        return target;
    }

    /**
     * get Modified Properties
     *
     * @param source Object
     * @param target Object
     * @return String[] fields array
     */
    public static String[] getModifiedProperties(Object source, Object target) {
        String[] properties = getCommonFields(source.getClass(), target.getClass());
        List<String> result = new ArrayList<>();

        BeanWrapper src = new BeanWrapperImpl(source);
        BeanWrapper trg = new BeanWrapperImpl(target);
        Object sourceValue;
        Object targetValue;
        boolean equal;
        for (String propertyName : properties) {
            try {
                sourceValue = src.getPropertyValue(propertyName);
                targetValue = trg.getPropertyValue(propertyName);
                if (sourceValue instanceof String) {
                    sourceValue = ((String) sourceValue).trim();
                }
                if (targetValue instanceof String) {
                    targetValue = ((String) targetValue).trim();
                }
                equal = sourceValue != null && !sourceValue.equals(targetValue) || targetValue != null && !targetValue
                        .equals(sourceValue);
                if (equal) {
                    result.add(propertyName);
                }
            } catch (BeansException e) {
                // do nothing
            }
        }

        return result.toArray(new String[0]);
    }

    /**
     * get common existed field for source class and target class
     *
     * @param sourceClz Class
     * @param targetClz Class
     * @return String[]
     */
    public static String[] getCommonFields(Class<?> sourceClz, Class<?> targetClz) {
        Field[] sourceDeclaredFields = getDeclaredNotSyntheticFields(sourceClz);
        Field[] targetDeclaredFields = getDeclaredNotSyntheticFields(targetClz);
        List<String> fieldList = new ArrayList<>();
        for (Field s : sourceDeclaredFields) {
            for (Field t : targetDeclaredFields) {
                String fieldName = s.getName();
                if (fieldName != null && fieldName.equals(t.getName())) {
                    fieldList.add(fieldName);
                    break;
                }
            }
        }
        return fieldList.toArray(new String[0]);
    }

    /**
     * getEntity
     *
     * @param request HttpServletRequest
     * @return Map
     */
    public static Map<String, Object> getEntity(HttpServletRequest request) {
        Map<String, Object> datas;
        try {
            datas = new ObjectMapper().readValue(getRequestBody(request), new TypeReference<Map<String, Object>>() {
            });
        } catch (IOException e) {
            datas = null;
            log.warn("new ObjectMapper().readValue() error:", e);
        }
        return datas;
    }

    private static <T> T newInstance(Class<T> targetClass) {
        T tmpBean;
        try {
            tmpBean = targetClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } return tmpBean;
    }

    private static String pBean2Json(Object bean, JsonInclude.Include includeType) throws IOException {
        String json = null;
        if (bean != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JsonGenerator jsonGenerator = objectMapper.getFactory().createGenerator(outputStream, JsonEncoding.UTF8);
            objectMapper.setSerializationInclusion(includeType);
            objectMapper.writeValue(jsonGenerator, bean);
            json = outputStream.toString("UTF-8");
        }
        return json;
    }

    private static <T> T pJson2Bean(String json, Class<T> clz) throws IOException {
        ObjectMapper objectMapper = getObjectMapper();
        return objectMapper.readValue(json, clz);
    }

    private static <T> T pJson2Bean(String json, Class<?> clz, Class<?>... parameterizedTypeClass) throws IOException {
        ObjectMapper objectMapper = getObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(clz, parameterizedTypeClass);
        return objectMapper.readValue(json, javaType);
    }


    private static String[] getProperties(Map<String, Object> datas, Class<?> clz) {
        Field[] fields = getDeclaredNotSyntheticFields(clz);
        List<String> propList = new ArrayList<>();
        if (datas != null) {
            JsonProperty jsonProperty;
            JsonIgnore jsonIgnore;
            String name;
            for (Field field : fields) {
                jsonIgnore = field.getAnnotation(JsonIgnore.class);
                if (jsonIgnore == null) {
                    jsonProperty = field.getAnnotation(JsonProperty.class);
                    if (jsonProperty == null) {
                        name = field.getName();
                    } else {
                        name = jsonProperty.value();
                    }
                    if (datas.get(name) != null) {
                        propList.add(field.getName());
                    }
                }
            }
        }
        return propList.toArray(new String[0]);
    }

    public static Field[] getDeclaredNotSyntheticFields(Class<?> clz) {
        Field[] declaredFields = clz.getDeclaredFields();
        Field[] filterFields;
        Stream<Field> stream = Arrays.stream(declaredFields);
        filterFields = stream.filter(f -> {
            // log just for this version
            if (f.isSynthetic() && !"$jacocoData".equalsIgnoreCase(f.getName())) {
                log.info("need to check unexpected synthetic field: " + clz.getName() + "%" + f.getName());
            }
            return !f.isSynthetic();
        }).toArray(Field[]::new);

        return filterFields;
    }

    private static ObjectMapper getObjectMapper() {
        return Singleton.INSTANCE.getInstance();
    }

    enum Singleton {

        INSTANCE;

        private final ObjectMapper objectMapper = new ObjectMapper();

        Singleton() {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }

        public ObjectMapper getInstance() {
            return objectMapper;
        }
    }

}



