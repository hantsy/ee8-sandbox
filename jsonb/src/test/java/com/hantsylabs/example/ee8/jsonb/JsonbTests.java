/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsonb;

import static com.hantsylabs.example.ee8.jsonb.PhoneNumber.Type.HOME;
import static com.hantsylabs.example.ee8.jsonb.PhoneNumber.Type.OFFICE;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import com.jayway.jsonpath.spi.mapper.GsonMappingProvider;
import java.io.File;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author hantsy
 */
@RunWith(Arquillian.class)
public class JsonbTests {

    @Deployment(name = "test")
    public static Archive<?> createDeployment() {
        File[] extraJars = Maven.resolver().loadPomFromFile("pom.xml")
                .resolve(
                        "com.jayway.jsonpath:json-path:2.4.0",
                        "com.google.code.gson:gson:2.8.2"
                )
                .withTransitivity()
                .asFile();
        WebArchive archive = ShrinkWrap.create(WebArchive.class)
                .addAsLibraries(extraJars)
                .addPackage(Person.class.getPackage())
                //.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addAsResource("persons.json", "persons.json")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        // System.out.println(archive.toString(true));
        return archive;
    }

    @Inject
    Logger LOG;

    @Test
    public void personToJsonString() {
        Person duke = new Person("Duke", LocalDate.of(1995, 5, 23));
        duke.setPhoneNumbers(
                Arrays.asList(
                        new PhoneNumber(HOME, "100000"),
                        new PhoneNumber(OFFICE, "200000")
                )
        );

        Jsonb jsonMapper = JsonbBuilder.create();
        String json = jsonMapper.toJson(duke);

        LOG.log(Level.INFO, "converted json result: {0}", json);

        String name = JsonPath.parse(json).read("$.name");
        assertEquals("Duke", name);

        Configuration config = Configuration.defaultConfiguration()
                .jsonProvider(new GsonJsonProvider())
                .mappingProvider(new GsonMappingProvider());
        TypeRef<List<String>> typeRef = new TypeRef<List<String>>() {
        };

        List<String> numbers = JsonPath.using(config).parse(json).read("$.phoneNumbers[*].number", typeRef);

        assertEquals(Arrays.asList("100000", "200000"), numbers);
    }

    @Test
    public void jsonStringToPerson() {
        //Jsonb jsonMapper = JsonbBuilder.create(new JsonbConfig().withFormatting(true).withNullValues(false));

        Jsonb jsonMapper = JsonbBuilder.create();

        Type type = new ArrayList<Person>() {
        }
                .getClass()
                .getGenericSuperclass();

        List<Person> persons = jsonMapper.fromJson(JsonbTests.class.getResourceAsStream("/persons.json"), type);

        assertTrue(persons.size() == 2);
    }
}
