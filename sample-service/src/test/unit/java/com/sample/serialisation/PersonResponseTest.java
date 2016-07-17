package com.sample.serialisation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.api.PersonResponse;

import io.dropwizard.jackson.Jackson;

import org.junit.Test;

import static io.dropwizard.testing.FixtureHelpers.fixture;

import static org.junit.Assert.assertEquals;

public class PersonResponseTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJson() throws Exception {
        final PersonResponse personResponse = new PersonResponse().setId(3).setName("Ray Hannity");
        assertEquals(fixture("fixtures/personresponse.json"), MAPPER.writeValueAsString(personResponse));
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final PersonResponse personResponse = new PersonResponse().setId(3).setName("Ray Hannity");
        assertEquals(personResponse, MAPPER.readValue(fixture("fixtures/personresponse.json"), PersonResponse.class));
    }
}
