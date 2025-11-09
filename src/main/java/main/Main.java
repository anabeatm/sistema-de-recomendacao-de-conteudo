package main;

import dao.ItemDAO;
import dao.RatingDAO;
import dao.UserDAO;
import domain.Item;
import domain.User;
import service.RecommendationService;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtil.getEntityManager();

        UserDAO userDAO = new UserDAO(em);
        ItemDAO itemDAO = new ItemDAO(em);
        RatingDAO ratingDAO = new RatingDAO(em);

        RecommendationService recommendationService = new RecommendationService(userDAO, ratingDAO, itemDAO);

        long userId = 50L;
        User user = userDAO.searchByID(userId);

        if (user == null) {
            System.out.println("ERRO: User com ID " + userId + " não encontrado!");
            em.close();
            return;
        }

        List<Item> recommendations = recommendationService.getRecommendationsByCategory(userId);

        if (recommendations.isEmpty()) {
            System.out.println("Nenhuma recomendação encontrada para este user com base nas suas categorias favoritas.");
        } else {
            System.out.println("Recomendações para " + user.getUserName() + ":");
            for (int i = 0; i < recommendations.size(); i++) {
                Item item = recommendations.get(i);
                System.out.println("  " + (i + 1) + ". " + item.getItemName() + " (Categoria: " + item.getCategory().getName() + ")");
            }
        }

        em.close();
    }
}