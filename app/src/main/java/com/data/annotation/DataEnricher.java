package com.data.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataEnricher {
    Type[] value();

    @interface Type {
        String serviceType();
    }
}
