package com.data.parser;

import com.data.factory.GenericModelFactory;
import com.data.model.GenericRequestV2;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.Optional;

public class ServiceRequestV2Deserializer  extends JsonDeserializer<GenericRequestV2> {
    private static final GenericModelFactory GENERIC_MODEL_FACTORY = new GenericModelFactory();
    public ServiceRequestV2Deserializer() {
    }

    @Override
    public GenericRequestV2 deserialize(JsonParser jsonParser,
                                        DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = (JsonNode)oc.readTree(jsonParser);
        String serviceType = node.get("service_type").asText();
        String operation = node.get("operation").asText();
        Optional<Class<? extends GenericRequestV2>> refOpt = GENERIC_MODEL_FACTORY.getModel(operation, serviceType);
        Class<? extends GenericRequestV2> refOpt2 = (Class) refOpt.orElseThrow(()->{
            return new IllegalArgumentException();
        });
        return (GenericRequestV2) jsonParser.getCodec().treeToValue(node, refOpt2);
    }
}
