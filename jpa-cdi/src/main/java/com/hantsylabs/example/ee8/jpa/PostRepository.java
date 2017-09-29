/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author hantsy
 */
@Stateless
public class PostRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Stream<Post> stream() {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<Post> q = cb.createQuery(Post.class);
        Root<Post> c = q.from(Post.class);
        TypedQuery query = this.entityManager.createQuery(q);

        return query.getResultStream();
    }

    public List<Post> findAll(String keyword) {
        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();

        CriteriaQuery<Post> q = cb.createQuery(Post.class);
        Root<Post> c = q.from(Post.class);

        List<Predicate> predicates = new ArrayList<>();

        if (null != keyword && "".equals(keyword.trim())) {
            predicates.add(
                    cb.or(
                            cb.like(c.get(Post_.title), '%' + keyword + '%'),
                            cb.like(c.get(Post_.content), '%' + keyword + '%')
                    )
            );
        }

        q.where(predicates.toArray(new Predicate[predicates.size()]));

        TypedQuery query = this.entityManager.createQuery(q);

        return query.getResultList();
    }

    public Post findById(Long id) {
        Post post = this.entityManager.find(Post.class, id);
        return post;
    }

    public Post save(Post post) {
        this.entityManager.persist(post);
        return post;
    }

    public Post update(Long id, Post post) {
        Post updated = this.entityManager.find(Post.class, id);
        updated.setTitle(post.getTitle());
        updated.setContent(post.getContent());
        updated.setTags(post.getTags());

        Post saved = this.entityManager.merge(post);
        return saved;
    }

    public void deleteById(Long id) {
        this.entityManager.remove(this.entityManager.find(Post.class, id));
    }

    public void delete(Post post) {
        this.entityManager.remove(this.entityManager.merge(post));
    }

}
