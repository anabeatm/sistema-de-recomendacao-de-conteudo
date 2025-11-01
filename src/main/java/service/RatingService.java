package service;

import dao.RatingDAO;
import dao.UserDAO;
import dao.ItemDAO;
import domain.Item;
import domain.User;
import domain.Rating;

import java.util.List;

public class RatingService {
    private final RatingDAO ratingDAO;
    private final UserDAO userDAO;
    private final ItemDAO itemDAO;

    public RatingService(RatingDAO ratingDAO, UserDAO userDAO, ItemDAO itemDAO){
        this.ratingDAO = ratingDAO;
        this.userDAO = userDAO;
        this.itemDAO = itemDAO;
    }

    public Rating ratingItem(Long userID, Long itemID, int value) {
        User user = userDAO.searchByID(userID);
        Item item = itemDAO.searchByID(itemID);

        if(user == null) throw new RuntimeException("Error: User with ID " +userID+ " not found.");
        if(item == null) throw new RuntimeException("Error: Item with ID " +itemID+ " not found.");

        Rating newRating = new Rating(user, item, value);

        return ratingDAO.save(newRating);
    }

    public List<Rating> findUserRatings(Long userID) {
        User user = userDAO.searchByID(userID);
        if(user == null) throw new RuntimeException("Error: User with ID " +userID+ " not found.");

        return ratingDAO.findByUser(user);
    }

    public List<Rating> findItemRatings(Long itemID) {
        Item item = itemDAO.searchByID(itemID);
        if(item == null) throw new RuntimeException("Error: Item with ID " +itemID+ " not found.");

        return ratingDAO.findByItem(item);
    }

}
