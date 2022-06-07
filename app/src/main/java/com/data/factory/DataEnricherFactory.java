package com.data.factory;

import com.data.annotation.DataEnricher;
import com.data.handler.DataEnricherHandler;

public class DataEnricherFactory extends AnnotationFactory<DataEnricher, DataEnricherHandler>{
    @Override
    protected Class<? extends DataEnricher> getAnnotation() {
        return null;
    }

    @Override
    protected Class<? extends DataEnricherHandler> getHandler() {
        return null;
    }

    @Override
    protected void registerHandler(Class<? extends DataEnricherHandler> var1) {

    }
}
