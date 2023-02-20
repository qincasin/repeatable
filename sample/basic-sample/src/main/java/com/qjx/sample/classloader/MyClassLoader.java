package com.qjx.sample.classloader;

/**
 * <Description>
 *
 * @author qinjiaxing on 2023/2/1
 * @author <others>
 */
public class MyClassLoader extends ClassLoader{
    private String classPath;

    public MyClassLoader( String classPath) {
        this.classPath = classPath;
    }




    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        return null;
    }
}
