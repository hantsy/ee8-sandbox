/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author hantsy
 */
@RunWith(Arquillian.class)
public class PostPersistenceTest {

    @Deployment(name = "test")
    public static Archive<?> createDeployment() {
        JavaArchive archive = ShrinkWrap.create(JavaArchive.class)
                //domain.support package.
                .addPackage(Post.class.getPackage())
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        // System.out.println(archive.toString(true));
        return archive;
    }

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    UserTransaction utx;

    private static final String TITLE = "test_title";
    private static final String CONTENT = "test_content";

    @Test
    public void testPersistPost() throws Exception {
        assertNotNull(entityManager);
        utx.begin();
        entityManager.joinTransaction();
        Post _post = Fixtures.newPost(TITLE, CONTENT);
        entityManager.persist(_post);

        assertNotNull(_post.getId());
        assertNotNull(_post.getCreatedAt());
        utx.commit();

        Post _saved = entityManager.find(Post.class, _post.getId());
        
        assertNotNull(_saved.getCreatedAt());
    }

}
