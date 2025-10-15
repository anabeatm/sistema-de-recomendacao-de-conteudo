package main;

import dao.UserDAO;
import domain.User;
import util.JPAUtil;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        UserDAO userDAO = new UserDAO(em);

        System.out.println("cadastrando alguns usuários para teste:");
        User ana = new User("Ana", "ana123@email.com");
        User pedro = new User("Pedro", "pedro123@email.com");
        System.out.println("usuarios criados na classe User.");

        userDAO.save(ana);
        userDAO.save(pedro);

        System.out.println(ana.getId());
        System.out.println(pedro.getId());

    }
}
