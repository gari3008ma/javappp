package com.data.parser;

import com.data.factory.ServiceModelFactory;
import com.data.model.ServiceRequestV2;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Optional;

public class ServiceRequestV2Deserializer  extends JsonDeserializer<ServiceRequestV2> {
    private static final ServiceModelFactory serviceModelFactory = new ServiceModelFactory();
    public ServiceRequestV2Deserializer() {
    }

    @Override
    public ServiceRequestV2 deserialize(JsonParser jsonParser,
                                        DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = (JsonNode)oc.readTree(jsonParser);
        String serviceType = node.get("service_type").asText();
        String operation = node.get("operation").asText();
        Optional<Class<? extends ServiceRequestV2>> refOpt = serviceModelFactory.getModel(operation, serviceType);
        Class<? extends ServiceRequestV2> refOpt2 = (Class) refOpt.orElseThrow(()->{
            return new IllegalArgumentException();
        });
        return (ServiceRequestV2) jsonParser.getCodec().treeToValue(node, refOpt2);
    }
}
