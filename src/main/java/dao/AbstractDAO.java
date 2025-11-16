package dao;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

// abstract class with basic abstract methods: like save, update, remove, search by ID and list all in the database
// where it implements the same methods from InterfaceDAO
public abstract class AbstractDAO<E, ID> implements InterfaceDAO<E, ID>{
    protected final EntityManager em;
    private final Class<E> entityClass;

    @SuppressWarnings("unchecked")
    // suppresses the compiler warning about the “unchecked” cast ((Class<E>)),
    // since the compiler cannot guarantee at compile time that the cast is correct
    public AbstractDAO(EntityManager em) {
        this.em = em;
        this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        allows a generic class to find out what the actual type of its generic parameter <E> is
    }

    public EntityManager getEm() {
        return this.em;
    }

    @Override
    public E save(E entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();

        return entity;
    }

    @Override
    public void update(E entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();

    }

    @Override
    public void remove(E entity) {
        em.getTransaction().begin();

        if (em.contains(entity)) {
            em.remove(entity);
        } else {
            em.remove(em.merge(entity));
        }

        em.getTransaction().commit();
    }

    @Override
    public E searchByID(ID id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<E> listAll() {
        String query = "FROM " + entityClass.getSimpleName();
        return em.createQuery(query, entityClass).getResultList();
    }
}
