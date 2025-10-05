package dao;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbastractDAO <E, ID> implements InterfaceDAO<E, ID>{
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
    }

//    TODO implementar métodos abstratos
    @Override
    public void att(E entidade) {

    }

    @Override
    public void remove(E entidade) {

    }

    @Override
    public E searchByID(ID id) {
        return null;
    }

    @Override
    public List<E> listAll() {
        return List.of();
    }
}
