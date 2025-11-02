package main;

import dao.CategoryDAO;
import dao.ItemDAO;
import dao.RatingDAO;
import dao.UserDAO;
import domain.*;
import util.JPAUtil;

import javax.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando...");

        EntityManager em = JPAUtil.getEntityManager();
        UserDAO userDAO = new UserDAO(em);
        ItemDAO itemDAO = new ItemDAO(em);
        RatingDAO ratingDAO = new RatingDAO(em);
        CategoryDAO categoryDAO = new CategoryDAO(em);

        User ana = new User("Ana", "ana@email.com");
        User bruno = new User("Bruno", "bruno@email.com");
        userDAO.save(ana);
        userDAO.save(bruno);

        Category catSciFi = new Category("Sci-Fi");
        Category catRock = new Category("Rock");
        categoryDAO.save(catSciFi);
        categoryDAO.save(catRock);

        Item filmMatrix = new Film("The Matrix", TypeItem.FILM, catSciFi, 136, "Wachowski");
        Item musicQueen = new Music("Bohemian Rhapsody", TypeItem.MUSIC, catRock, "Queen", "A Night at the Opera");
        Item filmInception = new Film("Inception", TypeItem.FILM, catSciFi, 148, "Christopher Nolan");
        itemDAO.save(filmMatrix);
        itemDAO.save(musicQueen);
        itemDAO.save(filmInception);

        Rating ratingAnaMatrix = new Rating(ana, filmMatrix, 5); // Ana amou Matrix
        Rating ratingAnaInception = new Rating(ana, filmInception, 4); // Ana gostou de Inception
        Rating ratingBrunoMatrix = new Rating(bruno, filmMatrix, 5); // Bruno também amou Matrix
        Rating ratingBrunoQueen = new Rating(bruno, musicQueen, 5); // Bruno amou Queen
        ratingDAO.save(ratingAnaMatrix);
        ratingDAO.save(ratingAnaInception);
        ratingDAO.save(ratingBrunoMatrix);
        ratingDAO.save(ratingBrunoQueen);


    }
}
