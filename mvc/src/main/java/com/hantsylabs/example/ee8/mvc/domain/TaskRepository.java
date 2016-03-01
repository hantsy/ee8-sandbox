package com.hantsylabs.example.ee8.mvc.domain;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author hantsy
 */
@Stateless
public class TaskRepository {

    @PersistenceContext
    EntityManager em;

    public Task findById(Long id) {
        Task task = em.find(Task.class, id);
        if (task == null) {
            throw new TaskNotFoundException("task " + id + " not found !");
        }

        return task;
    }

    public List<Task> findByStatus(Task.Status status) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();

        CriteriaQuery<Task> q = cb.createQuery(Task.class);
        Root<Task> c = q.from(Task.class);
        q.where(cb.equal(c.get(Task_.status), status));

        TypedQuery<Task> query = em.createQuery(q);

        return query.getResultList();
    }

    public Task save(Task task) {
        em.persist(task);

        return task;
    }

    public Task update(Task task) {
        return em.merge(task);
    }

    public void delete(Task task) {
        em.remove(task);
    }

    public void deleteById(Long id) {
        Task task = em.find(Task.class, id);
        if (task == null) {
            throw new TaskNotFoundException("task '" + id + "' not found !");
        }

        em.remove(task);
    }

}