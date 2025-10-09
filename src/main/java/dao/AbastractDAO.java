package dao;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbastractDAO <E, ID> implements InterfaceDAO<E, ID>{
    protected final EntityManager em;
    private final Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public AbastractDAO(EntityManager em) {
        this.em = em;
        this.entityClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        permite que uma classe genérica descubra qual é o tipo real do seu parâmetro genérico <E>
    }

    @Override
    public void save(E entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public void att(E entity) {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();

        em.close();
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

        em.close();
    }

    @Override
    public E searchByID(ID id) {
        return em.find(entityClass, id);
    }

    @Override
    public List<E> listAll() {
        String query = "FROM" + entityClass.getSimpleName();
        return em.createQuery(query, entityClass).getResultList();
    }
}
