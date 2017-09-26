/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.rest;

import com.hantsylabs.example.ee8.rest.PhoneNumber.Type;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author hantsy
 */
@ApplicationScoped
public class PersonRepository implements Serializable{

    private final Map<Long, Person> persons = new HashMap<>();

    private final AtomicLong nextIdGenerator = new AtomicLong(1L);

    public PersonRepository() {

        Person person = new Person(nextId(), "Hantsy", "Bai", LocalDate.of(1978, 1, 1));

        List<PhoneNumber> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(new PhoneNumber(Type.OFFICE, "13912345678"));
        person.setPhoneNumbers(phoneNumbers);

        Person foobar = new Person(nextId(), "foo", "bar", LocalDate.of(1990, 12, 31));
        List<PhoneNumber> foobarphoneNumbers = new ArrayList<>();
        foobarphoneNumbers.add(new PhoneNumber(Type.HOME, "13911111111"));
        foobar.setPhoneNumbers(foobarphoneNumbers);

        persons.put(person.getId(), person);
        persons.put(person.getId(), foobar);
    }

    private Long nextId() {
        return nextIdGenerator.getAndIncrement();
    }

    List<Person> findAll() {
        return new ArrayList<>(persons.values());
    }

    Person findById(Long id) {
        return persons.get(id);
    }

    Person save(Person person) {
        Long id = nextId();
        person.setId(id);
        persons.put(id, person);
        return person;
    }

    Person update(Long id, Person person) {
        Person updated = persons.get(id);
        updated.setFirstName(person.getFirstName());
        updated.setLastName(person.getLastName());
        updated.setBirthDate(person.getBirthDate());
        persons.put(id, updated);
        return updated;
    }

    Person delete(Long id) {
        Person deleted = persons.get(id);
        persons.remove(id);
        return deleted;
    }

}
