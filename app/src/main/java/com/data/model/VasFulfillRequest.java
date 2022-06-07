package com.data.model;

import com.data.annotation.ServiceContext;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonDeserialize(
        using = JsonDeserializer.None.class
)
@JsonIgnoreProperties
@JsonNaming(SnakeCaseStrategy.class)
@ServiceContext({@ServiceContext.Type(operation = "FULFILL", serviceType = "AMC") ,
        @ServiceContext.Type(operation = "FULFILL", serviceType = "OTSR")})
public class VasFulfillRequest implements ServiceRequestV2 {
    private String referenceId;
    private String ServiceType;
    private String ServiceProvider;
    private String operation;

    @Override
    public String getReferenceId() {
        return this.referenceId;
    }

    @Override
    public String getServiceType() {
        return this.ServiceType;
    }

    @Override
    public String getServiceProvider() {
        return this.ServiceProvider;
    }

    @Override
    public String getOperation() {
        return this.operation;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public void setServiceType(String serviceType) {
        ServiceType = serviceType;
    }

    public void setServiceProvider(String serviceProvider) {
        ServiceProvider = serviceProvider;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
