package service;

import dao.*;
import domain.*;

import java.util.*;

public class RecommendationService {
    private final UserDAO userDAO;
    private final RatingDAO ratingDAO;
    private final ItemDAO itemDAO;

    public RecommendationService(UserDAO userDAO, RatingDAO ratingDAO, ItemDAO itemDAO) {
        this.userDAO = userDAO;
        this.ratingDAO = ratingDAO;
        this.itemDAO = itemDAO;
    }


    private boolean listContainsCategory(List<Category> list, Category category){
        for(Category c : list){
            if(c.equals(category)){
                return true;
            }
        }
        return false;
    }

    // ter um método pra cada recomendação
    // como funcionaria: 1. buscar por todos os filmes da msm categoria que o user avaliou
    // 2. buscar filmes da mesma categorias que outras pessoas avaliaram
    // 3. o maior avaliado, vai ser recomendado dentro do genero

    public List<Item> getRecommendationsByCategory(Long userID, TypeItem requiredType) {
//        encontrar categorias favs
        User targetUser = userDAO.searchByID(userID);
        if(targetUser == null) throw new IllegalArgumentException("User not found!");

        List<Rating> highRatings = ratingDAO.findHighRatingsByUser(targetUser);
        List<Category> favoriteCategories = new ArrayList<>();

        for(Rating r : highRatings) {
            Category category = r.getItem().getCategory();
            if(!listContainsCategory(favoriteCategories, category)) {
                favoriteCategories.add(category);
            }
        }

        if(favoriteCategories.isEmpty()) return new ArrayList<>();

//      encontrar itens não avaliados (ou seja, nao viu/ouviu)
        List<Rating> allUserRatings = ratingDAO.findByUser(targetUser);
        List<Item> seenItems = new ArrayList<>();
        for(Rating r : allUserRatings) {
            seenItems.add(r.getItem());
        }

//      encontrar e pontuar possiveis recomendacoes
        List<Item> allItems = itemDAO.listAll();
        List<ItemAverageScore> candidateScores = new ArrayList<>();

        for(Item candidate : allItems) {
            boolean notSeen = !seenItems.contains(candidate);
            boolean inFavoriteCategory = candidate.getCategory() != null && listContainsCategory(favoriteCategories, candidate.getCategory());

            if(notSeen && inFavoriteCategory) {
                List<Rating> allRatingsForItem = ratingDAO.findByItem(candidate);
                if(!allRatingsForItem.isEmpty()) {
                    double sum = 0;
                    for(Rating r : allRatingsForItem) {
                        sum += r.getRating();
                    }

                    double average = sum / allRatingsForItem.size();
                    candidateScores.add(new ItemAverageScore(candidate, average));
                }
            }
        }

        Collections.sort(candidateScores); // ascending order

        List<Item> finalRecommendations = new ArrayList<>();

        for(ItemAverageScore itemAverageScore : candidateScores) {
            Item item = itemAverageScore.getItem();

            if(item.getType() == requiredType) {
                finalRecommendations.add(item);
            }

            if(finalRecommendations.size() >= 10) {
                break;
            }
        }

        return finalRecommendations;
    }

    private static class ItemAverageScore implements Comparable<ItemAverageScore> {
        private final Item item;
        private final double averageScore;

        public ItemAverageScore(Item item, double averageScore){
            this.item = item;
            this.averageScore = averageScore;
        }

        public Item getItem(){return item;}

        @Override
        public int compareTo(ItemAverageScore other) {
            return Double.compare(other.averageScore, this.averageScore); //ordena da maior p/ menor
        }
    }

    private static class ItemScore implements Comparable<ItemScore> {
        private final Item item;
        private int score;

        public ItemScore(Item item, int score) {
            this.item = item;
            this.score = score;
        }

        public Item getItem(){return item;}
        public int getScore(){return score;}
        public void incrementScore(){this.score++;}

        @Override
        public int compareTo(ItemScore other){
            return Integer.compare(other.score, this.score);
        }
    }
}
