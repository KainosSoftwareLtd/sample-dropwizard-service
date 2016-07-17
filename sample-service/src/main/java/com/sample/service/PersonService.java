package com.sample.service;

import com.sample.domain.Person;

import java.util.Arrays;
import java.util.List;

public class PersonService {

    // this is obviously all just mock data
    private Person person1 = new Person().setId(1).setName("John Smith");
    private Person person2 = new Person().setId(2).setName("Jane Doe");

    public PersonService() {

    }

    public List<Person> getAll() {
        return Arrays.asList(person1, person2);
    }

    public Person get(Integer id){
        switch (id) {
            case 1: return person1;
            case 2: return person2;
            default: return null;
        }
    }
}
