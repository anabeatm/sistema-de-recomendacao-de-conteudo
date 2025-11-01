package dao;

import domain.Category;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class CategoryDAO extends AbstractDAO<Category, Long> {

    public CategoryDAO(EntityManager em) {
        super(em);
    }

    public Category findByName(String name) {
        try{
            TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.name = :name", Category.class);
            query.setParameter("name", name);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


}
