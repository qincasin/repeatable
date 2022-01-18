package com.qjx.repeatable.runner;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author qinjiaxing
 */
public class Description implements Serializable {
    public static final long serialVersionUID = 1L;

    private final String fDisplayName;
    private final Serializable fUniqueId;
    private volatile Class<?> fTestClass;


    public Description(Class<?> fTestClass) {
        this.fTestClass = fTestClass;
        this.fDisplayName = fTestClass.getName();
        this.fUniqueId = fTestClass.getName();
    }

    public Description(Class<?> fTestClass, String name) {
        this.fDisplayName = formateDisplayName(name, fTestClass.getName());
        this.fTestClass = fTestClass;
        this.fUniqueId = formateDisplayName(name, fTestClass.getName());

    }

    public String getfDisplayName() {
        return fDisplayName;
    }

    public Class<?> getfTestClass() {
        return fTestClass;
    }

    private static String formateDisplayName(String name, String className) {
        return String.format("%s(%s)", name, className);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Description that = (Description) o;
        return Objects.equals(fUniqueId, that.fUniqueId);
    }
}
