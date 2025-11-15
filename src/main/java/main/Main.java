package main;

import dao.ItemDAO;
import dao.RatingDAO;
import dao.UserDAO;
import domain.Item;
import domain.User;
import service.RecommendationService;
import util.DatabaseSeeder;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        DatabaseSeeder.run();

        long endTime = System.currentTimeMillis();
        System.out.println("Tempo total do seeder: " + (endTime - startTime) / 1000 + " segundos.");
    }
}