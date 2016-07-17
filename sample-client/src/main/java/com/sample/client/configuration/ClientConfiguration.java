package com.sample.client.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ClientConfiguration {
    @Valid
    @NotNull
    @JsonProperty("host")
    public String host;

    @Valid
    @NotNull
    @JsonProperty("port")
    public Integer port;

    @Valid
    @NotNull
    @JsonProperty("serviceContext")
    public String serviceContext;
}
