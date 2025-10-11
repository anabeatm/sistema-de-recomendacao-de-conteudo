package dao;

import domain.Item;
import domain.Rating;
import domain.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class RatingDAO extends AbstractDAO<Rating, Long>{
    public RatingDAO(EntityManager em) {
        super(em);
    }

    public List<Rating> findByUser(User user) {
        TypedQuery<Rating> query = em.createQuery("SELECT r FROM Rating r WHERE r.user = :user", Rating.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public List<Rating> findByItem(Item item) {
        TypedQuery<Rating> query = em.createQuery("SELECT r FROM Rating r WHERE r.item = :item", Rating.class);
        query.setParameter("item", item);
        return query.getResultList();
    }

    public List<Rating> findHighRatingsByUser(User user) {
        TypedQuery<Rating> query = em.createQuery("SELECT r FROM Rating r WHERE r.user = :user AND r.rating >= 4", Rating.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public List<Rating> findHighRatingsByItem(Item item) {
        TypedQuery<Rating> query = em.createQuery("SELECT r FROM Rating r WHERE r.item = :item AND r.rating >= 4", Rating.class);
        query.setParameter("item", item);
        return query.getResultList();
    }
}
