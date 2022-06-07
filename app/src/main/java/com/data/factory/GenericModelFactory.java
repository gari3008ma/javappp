package com.data.factory;

import com.data.annotation.GenericContext;
import com.data.model.GenericRequestV2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class GenericModelFactory extends AnnotationFactory<GenericContext, GenericRequestV2>{
    private static final Map<String, Map<String, Class<? extends GenericRequestV2>>> repository = new HashMap();

    public GenericModelFactory() {
    }

    @Override
    protected Class<? extends GenericContext> getAnnotation() {
        return GenericContext.class;
    }

    @Override
    protected Class<? extends GenericRequestV2> getHandler() {
        return GenericRequestV2.class;
    }

    protected void registerHandler(Class<? extends GenericRequestV2> var1) {
        GenericContext genericContext = (GenericContext)var1.getAnnotations(GenericContext.class);

        if(Objects.nonNull(genericContext)) {
            GenericContext.Type[]  var3 = genericContext.value();
            int var4 = var3.length;

            for(int var5=0; var5<var4; ++var5) {
                GenericContext.Type subAnno = var3[var5];
                String operation = subAnno.operation();
                if(!repository.containsKey(operation)) {
                    repository.put(operation, new HashMap());
                }

                ((Map) repository.get(operation)).put(subAnno.serviceType(), var1);
            }
        }
    }

    public Optional<Class<? extends GenericRequestV2>> getModel(String operation, String serviceType) {
        return Optional.ofNullable(((Map)repository.getOrDefault(operation, new HashMap())).get(serviceType));
    }
}
