package main;

import dao.ItemDAO;
import dao.UserDAO;
import domain.*;
import util.JPAUtil;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        UserDAO userDAO = new UserDAO(em);
        ItemDAO itemDAO = new ItemDAO(em);

        System.out.println("cadastrando alguns usuários para teste:");
        User ana = new User("Ana", "ana123@email.com");
        User pedro = new User("Pedro", "pedro123@email.com");


//        userDAO.save(ana);
//        userDAO.save(pedro);
//
//        System.out.println(ana.getId());
//        System.out.println(pedro.getId());
        System.out.println("cadastrando alguns itens");
        Item avalonMusic = new Music("Avalon", TypeItem.MUSIC, "KR&B", "DPR IAN", "Moodswings");
        Film howToTrainYourDragonFilm = new Film("How to train your dragon", TypeItem.FILM, "Comédia", 120, "sei lá");


        System.out.println(howToTrainYourDragonFilm.getFormattedDuration());

    }
}
