/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hantsylabs.example.ee8.jpa;

import java.util.List;
import static java.util.stream.Collectors.toList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author hantsy
 */
@Stateless
public class PostRepository extends AbstractRepository<Post, Long> {

    @PersistenceContext
    private EntityManager entityManager;

//
//    public List<Post> findByKeyword(String keyword) {
//        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<Post> q = cb.createQuery(Post.class);
//        Root<Post> c = q.from(Post.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (null != keyword && "".equals(keyword.trim())) {
//            predicates.add(
//                    cb.or(
//                            cb.like(c.get(Post_.title), '%' + keyword + '%'),
//                            cb.like(c.get(Post_.content), '%' + keyword + '%')
//                    )
//            );
//        }
//
//        q.where(predicates.toArray(new Predicate[predicates.size()]));
//
//        TypedQuery query = this.entityManager.createQuery(q);
//
//        return query.getResultList();
//    }
    
    
    public List<Post> findByKeyword(String keyword) {
        return stream().filter(p -> p.getTitle().contains(keyword) || p.getContent().contains(keyword))
                .collect(toList());
    }
    
    public long countByKeyword(String keyword) {
        return stream().filter(p -> p.getTitle().contains(keyword) || p.getContent().contains(keyword))
                .count();
    }

    @Override
    protected EntityManager entityManager() {
        return this.entityManager;
    }

}
