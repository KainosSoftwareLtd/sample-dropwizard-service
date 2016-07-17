package com.sample.resource;

import com.sample.api.PersonResponse;
import com.sample.domain.Person;

public  class PersonResponseMapper {

    public static PersonResponse fromPerson(Person p) {
        PersonResponse response =  new PersonResponse();
        response.setId(p.getId());
        response.setName(p.getName());
        return response;
    }
}
