package com.sample.client;

import com.sample.api.PersonResponse;
import com.sample.client.configuration.ClientConfiguration;

import io.dropwizard.client.JerseyClientConfiguration;
import io.dropwizard.setup.Environment;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

public class PersonClient extends BaseClient {

    public PersonClient(Environment environment, ClientConfiguration clientConfiguration,
                        JerseyClientConfiguration jerseyClientConfiguration) {
        super(environment, clientConfiguration, jerseyClientConfiguration, "PersonClient");
    }

    public List<PersonResponse> getAll() {
        return webTarget
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<PersonResponse>>() {  });
    }

    public PersonResponse get(Integer id) {
        return webTarget
                .path(id.toString())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(PersonResponse.class);
    }

    public PersonResponse add(PersonResponse personResponse) {
        return webTarget
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(personResponse, MediaType.APPLICATION_JSON_TYPE), PersonResponse.class);
    }

    public PersonResponse update(Integer id, PersonResponse personResponse) {
        return webTarget
                .path(id.toString())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(personResponse, MediaType.APPLICATION_JSON_TYPE), PersonResponse.class);
    }

    public void delete(Integer id) {
        webTarget
                .path(id.toString())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .delete();
    }
}
