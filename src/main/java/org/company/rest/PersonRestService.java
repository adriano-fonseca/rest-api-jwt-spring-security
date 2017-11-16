package org.company.rest;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/api/persons")
public class PersonRestService {
    private static final List<Person> persons;

    static {
        persons = new ArrayList<>();
        persons.add(new Person("Hello", "World"));
        persons.add(new Person("Foo", "Bar"));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation("Get all Persons")
    public List<Person> getPersons() {
    	for(Person person : persons) {
    		Link selfLink = linkTo(PersonRestService.class).slash(person.getName()).withSelfRel();
    		person.add(selfLink);
    	}

        return persons;
    }
    
    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    @ApiOperation("Gets a Person with a specific name")
    @ApiResponses(value = {@ApiResponse(code = 200, message="OK", response = Person.class)})
    public static Person getPerson(@PathVariable("name") String name) {
        return persons.stream()
                .filter(person -> name.equalsIgnoreCase(person.getName()))
                .findAny().orElse(null);
    }
}
