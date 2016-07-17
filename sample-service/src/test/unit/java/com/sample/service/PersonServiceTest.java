package com.sample.service;

import com.sample.domain.Person;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonServiceTest {
    private PersonService service;

    @Before
    public void setup() {
        service = new PersonService();
    }

    @Test
    public void get() {
        Person result = service.get(1);
        assertEquals("John Smith", result.getName());
    }

    @Test
    public void getAll() {
        List<Person> result = service.getAll();

        assertEquals(2, result.size());
        assertEquals("John Smith", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());
    }
}
