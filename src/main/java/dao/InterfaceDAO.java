package dao;

import java.util.List;

// <<‘Interface’>> :: é uma estrutura que define um contrato de comportamento que uma classe deve seguir <<AbstractDAO>>,
// especificando um conjunto de métodos abstratos que devem ser implementados por qualquer classe que
// declare implementá-la.
public interface InterfaceDAO<E, ID> {
    E save(E entity);

    void update(E entity);

    void remove(E entity);

    E searchByID(ID id);

    List<E> listAll();
}
