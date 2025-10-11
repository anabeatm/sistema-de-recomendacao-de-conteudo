package dao;

import domain.User;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class UserDAO extends AbstractDAO<User, Long> {

    public UserDAO(EntityManager em) {
        super(em);
    }

    public User findByEmail(String email) {
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.user_email = :email", User.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
