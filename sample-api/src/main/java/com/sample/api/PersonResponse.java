package com.sample.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class PersonResponse {
    @JsonProperty
    private Integer id;

    @NotNull
    @JsonProperty
    private String name;

    public PersonResponse() {
    }

    public Integer getId() {
        return id;
    }

    public PersonResponse setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PersonResponse setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonResponse)) return false;

        PersonResponse that = (PersonResponse) o;

        if (!getId().equals(that.getId())) return false;
        return getName().equals(that.getName());

    }
}
