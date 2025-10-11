package service;

import dao.*;
import domain.*;

import java.util.List;


public class RecommendationService {
    private final UserDAO userDAO;
    private final RatingDAO ratingDAO;

    public RecommendationService(UserDAO userDAO, RatingDAO ratingDAO) {
        this.userDAO = userDAO;
        this.ratingDAO = ratingDAO;
    }

    public List<Item> getRecommendationForUser(Long userID) {
        User targetUser = userDAO.searchByID(userID);
        if(targetUser == null) {
            throw new IllegalArgumentException("User not found!");
        }
//      TODO implementar o método de recomendação
        return List.of();
    }
}
