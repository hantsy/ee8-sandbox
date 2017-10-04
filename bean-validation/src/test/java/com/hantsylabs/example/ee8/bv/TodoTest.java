/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.bv;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author hantsy
 */
@RunWith(Arquillian.class)
public class TodoTest {

    @Deployment(name = "test")
    public static Archive<?> createDeployment() {

        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                .addPackage(Todo.class.getPackage())
                //.addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                //.addAsResource("persons.json", "persons.json")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        // System.out.println(archive.toString(true));
        return archive;
    }

    @Inject
    Logger LOG;

    @Inject
    ValidatorFactory validatorFactory;

    @Inject
    Validator validator;

    @Test()
    public void testInvalidTodo() {
        Todo todo = new Todo();
        assertNull(todo.getName());
        Set<ConstraintViolation<Todo>> violations = validatorFactory.getValidator().validate(todo);
        LOG.log(Level.INFO, "violations.size ::" + violations.size());
        assertTrue(violations.size() > 0);
    }

    @Test()
    public void testInvalidTodo2() {
        Todo todo = new Todo();
        Set<ConstraintViolation<Todo>> violations = validator.validate(todo, Default.class);
        LOG.log(Level.INFO, "violations.size ::" + violations.size());
        assertTrue(violations.size() > 0);
    }

    @Test()
    public void testInvalidDueDate() {
        Todo todo = new Todo();
        todo.setName("test");
        todo.setDueDate(LocalDateTime.now());
        Set<ConstraintViolation<Todo>> violations = validator.validate(todo, Default.class);
        LOG.log(Level.INFO, "violations.size ::" + violations.size());
        assertTrue(violations.size() == 1);
    }

}
