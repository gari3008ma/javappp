package com.data.model;

import com.data.parser.ServiceRequestV2Deserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(
        using= ServiceRequestV2Deserializer.class
)
public interface ServiceRequestV2 {
    String getReferenceId();
    String getServiceType();
    String getServiceProvider();
    String getOperation();
}
