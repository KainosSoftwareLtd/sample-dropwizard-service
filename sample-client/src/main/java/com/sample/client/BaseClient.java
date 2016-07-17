package com.sample.client;

import com.sample.client.configuration.ClientConfiguration;

import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.setup.Environment;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

public abstract class BaseClient {
    private final Client httpClient;

    protected WebTarget webTarget;

    public BaseClient(Environment environment, ClientConfiguration clientConfiguration,
                            JerseyClientConfiguration jerseyClientConfiguration, String clientName) {
        httpClient = new JerseyClientBuilder(environment)
                .using(jerseyClientConfiguration)
                .build(clientName);

        StringBuilder targetBuilder = new StringBuilder();

        String target = targetBuilder.append(clientConfiguration.host)
                .append(':')
                .append(clientConfiguration.port)
                .append('/')
                .append(clientConfiguration.serviceContext).toString();

        webTarget = httpClient.target(target);
    }
}
