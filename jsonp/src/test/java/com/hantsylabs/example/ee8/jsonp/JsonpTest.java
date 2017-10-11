/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jsonp;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonPatch;
import javax.json.JsonPointer;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.bind.JsonbBuilder;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonParser;
import static javax.json.stream.JsonParser.Event.KEY_NAME;
import static javax.json.stream.JsonParser.Event.START_ARRAY;
import static javax.json.stream.JsonParser.Event.VALUE_STRING;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author hantsy
 */
@RunWith(Arquillian.class)
public class JsonpTest {

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
                .addAsResource("persons.json")
                .addAsResource("person.json")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        // System.out.println(archive.toString(true));
        return archive;
    }

    @Inject
    Logger LOG;

    @Test
    public void testJsonParser() {
        String key = "";
        String value = "";
        JsonParser parser = Json.createParser(JsonpTest.class.getResourceAsStream("/person.json"));
        while (parser.hasNext()) {
            JsonParser.Event e = parser.next();
            System.out.print(e.name());

            switch (e) {
                case START_ARRAY:
                    if (key.equals("phoneNumbers")) {
                        parser.skipArray();
                    }
                    break;
                case KEY_NAME:
                    key = parser.getString();
                    System.out.print(" - " + parser.getString());
                    break;
                case VALUE_STRING:
                    System.out.print(" - " + parser.getString());
                    if ("name".equals(key)) {
                        value = parser.getString();
                    }
            }

            System.out.println();
        }

        assertEquals("Duke", value);
    }

    @Test
    public void testJsonGenerator() {
        JsonGenerator gen = Json.createGenerator(System.out);
        gen.writeStartArray()
                .writeStartObject()
                .write("name", "Duke")
                .write("birthData", "1995-5-23")
                .writeStartArray("phoneNumbers")
                .writeStartObject()
                .write("type", "home")
                .write("number", "123 456 789")
                .writeEnd()
                .writeEnd()
                .writeEnd()
                .writeEnd()
                .flush();

    }

    @Test
    public void testJsonStream() {
        JsonReader reader = Json.createReader(JsonpTest.class.getResourceAsStream("/persons.json"));
        List<String> nameList = reader.readArray().stream()
                .map(o -> o.asJsonObject().getJsonString("name").getString())
                .collect(toList());

        assertEquals(Arrays.asList("Duke", "Hantsy"), nameList);

    }

    @Test
    public void testJsonPointer() {
        JsonReader reader = Json.createReader(JsonpTest.class.getResourceAsStream("/persons.json"));

        JsonArray arrays = reader.readArray();

        JsonPointer p = Json.createPointer("/0/name");
        JsonValue name = p.getValue(arrays);

        System.out.println("json value ::" + name);

       // assertEquals("Duke", name.toString());

        JsonReader objReader = Json.createReader(JsonpTest.class.getResourceAsStream("/person.json"));
        JsonPointer p2 = Json.createPointer("/name");
        JsonValue name2 = p2.getValue(objReader.readObject());
        System.out.println("json value ::" + name2);
      //  assertEquals("Duke", name2.toString());
    }

    @Test
    public void testJsonPatch() {
        JsonReader reader = Json.createReader(JsonpTest.class.getResourceAsStream("/persons.json"));
        JsonArray jsonaArray = reader.readArray();

        JsonPatch patch = Json.createPatchBuilder()        
                .replace("/0/name", "Duke Oracle")
                .remove("/1")
                .build();

        JsonArray result = patch.apply(jsonaArray);
        System.out.println(result.toString());
        
        Type type = new ArrayList<Person>() {}.getClass().getGenericSuperclass();

        List<Person> person = JsonbBuilder.create().fromJson(result.toString(), type);
        assertEquals("Duke Oracle", person.get(0).getName());

    }

}
