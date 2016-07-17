package com.sample.resource;

import com.sample.api.PersonResponse;
import com.sample.domain.Person;
import com.sample.service.PersonService;

import io.dropwizard.testing.junit.ResourceTestRule;

import org.junit.ClassRule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.GenericType;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonResourceTest {
    private static final PersonService PERSON_READ_SERVICE = mock(com.sample.service.PersonService.class);

    static {
        Logger.getLogger("com.sun.jersey").setLevel(Level.OFF);
    }

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new PersonResource(PERSON_READ_SERVICE))
            .build();

    @Test
    public void getAll() throws Exception {
        when(PERSON_READ_SERVICE.getAll()).thenReturn(getMockPeople());

        List<PersonResponse> result = resources.client().target("/people").request().get(new GenericType<List<PersonResponse>>() {});

        assertEquals(2, result.size());
        assertEquals("John Smith", result.get(0).getName());
    }

    @Test
    public void get() throws Exception {
        when(PERSON_READ_SERVICE.get(1)).thenReturn(getMockPerson1());

        PersonResponse personResponse = resources.client().target("/people/1").request().get(new GenericType<PersonResponse>() {});

        assertEquals("John Smith", personResponse.getName());
    }

    private Person getMockPerson1() {
        return new Person().setId(1).setName("John Smith");
    }

    private List<Person> getMockPeople() {
        List<Person> people = new ArrayList<>();
        people.add(new Person().setId(1).setName("John Smith"));
        people.add(new Person().setId(2).setName("Jane Doe"));
        return people;
    }
}
