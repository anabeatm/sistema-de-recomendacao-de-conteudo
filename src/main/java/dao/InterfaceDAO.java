package dao;

import java.util.List;

public interface InterfaceDAO<E, ID> {
    void save(E entidade);

    void att(E entidade);

    void remove(E entidade);

    E searchByID(ID id);

    List<E> listAll();
}
