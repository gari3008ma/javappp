package com.data.factory;

import com.data.annotation.ServiceContext;
import com.data.model.ServiceRequestV2;
import com.fasterxml.jackson.core.ObjectCodec;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class ServiceModelFactory extends AnnotationFactory<ServiceContext, ServiceRequestV2>{
    private static final Map<String, Map<String, Class<? extends  ServiceRequestV2>>> repository = new HashMap();

    public ServiceModelFactory() {
    }

    @Override
    protected Class<? extends ServiceContext> getAnnotation() {
        return ServiceContext.class;
    }

    @Override
    protected Class<? extends ServiceRequestV2> getHandler() {
        return ServiceRequestV2.class;
    }

    protected void registerHandler(Class<? extends ServiceRequestV2> var1) {
        ServiceContext serviceContext = (ServiceContext)var1.getAnnotations(ServiceContext.class);

        if(Objects.nonNull(serviceContext)) {
            ServiceContext.Type[]  var3 = serviceContext.value();
            int var4 = var3.length;

            for(int var5=0; var5<var4; ++var5) {
                ServiceContext.Type subAnno = var3[var5];
                String operation = subAnno.operation();
                if(!repository.containsKey(operation)) {
                    repository.put(operation, new HashMap());
                }

                ((Map) repository.get(operation)).put(subAnno.serviceType(), var1);
            }
        }
    }

    public Optional<Class<? extends ServiceRequestV2>> getModel(String operation, String serviceType) {
        return Optional.ofNullable(((Map)repository.getOrDefault(operation, new HashMap())).get(serviceType));
    }
}
