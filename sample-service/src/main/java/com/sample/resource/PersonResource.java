package com.sample.resource;

import com.codahale.metrics.annotation.Timed;
import com.sample.api.PersonResponse;
import com.sample.domain.Person;
import com.sample.service.PersonService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

@Path("/people")
@Api("Sample Dropwizard Service")
@Produces({MediaType.APPLICATION_JSON})
public class PersonResource {
    private final static Logger LOGGER = LoggerFactory.getLogger(PersonResource.class);

    PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }

    @Timed
    @GET
    @ApiOperation(
            value = "Get list of stored people",
            response = PersonResponse.class,
            responseContainer = "List"
    )
    public List<PersonResponse> getAll(){
        List<Person> allPersons = personService.getAll();
        return allPersons.stream().map(PersonResponseMapper::fromPerson).collect(Collectors.toList());
    }

    @Timed
    @GET
    @Path("/{id}")
    @ApiOperation(
            value = "Get a stored person",
            response = PersonResponse.class
    )
    public PersonResponse get(@PathParam("id") Integer id){
        Person p = personService.get(id);

        if (p == null) {
            LOGGER.info("Person not found with id of "+id);
            throw new WebApplicationException(404);
        }

        return PersonResponseMapper.fromPerson(p);
    }
}
