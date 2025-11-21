package view;

import dao.*;
import domain.*;
import service.*;
import util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class View {
    private final EntityManager em = JPAUtil.getEntityManager();
    private final Scanner scanner = new Scanner(System.in);

    private final UserDAO userDAO = new UserDAO(em);
    private final ItemDAO itemDAO = new ItemDAO(em);
    private final RatingDAO ratingDAO = new RatingDAO(em);
    private final CategoryDAO categoryDAO = new CategoryDAO(em);
    private final CategoryService categoryService = new CategoryService(categoryDAO);

    private final UserService userService = new UserService(userDAO);
    private final ItemService itemService = new ItemService(itemDAO, categoryService);
    private final RatingService ratingService = new RatingService(ratingDAO, userDAO, itemDAO);
    private final RecommendationService recommendationService = new RecommendationService(userDAO, ratingDAO, itemDAO);

    private User currentUser = null;

    public void run() {
        while(true) {
            if(currentUser == null) {
                startSystem();
            } else {
                runMainMenuLoop();
            }
        }
    }

    private void startSystem() {
        System.out.println("\n.\uD81A\uDD54 ݁ ˖๋ ࣭ ⭑. Recommendation System ! \uD81A\uDD54 ݁ ˖๋ ࣭ ⭑");
        System.out.println("[1] Create new user");
        System.out.println("[2] Enter");
        System.out.print("Input: ");

        int enter = -1;
        try {
            enter = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Invalidate option. Try again.");
            return;}

        if(enter == 1) {
            System.out.print("Username: ");
            String userName = scanner.nextLine();
            System.out.print("Email: ");
            String newEmail = scanner.nextLine();

            userService.registerNewUser(userName, newEmail);
        } else if(enter == 2){
            runLoginLoop();
        } else {
            System.out.println("Invalidate option. Try again.");
        }
    }

    private void runLoginLoop() {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();

        try{
            currentUser = userDAO.findByEmail(email);
            if(currentUser == null) {
                System.out.println("Error. Email not found!");
            } else {
                System.out.println("Welcome, " + currentUser.getUserName());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void runMainMenuLoop() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- MENU (").append(currentUser.getUserName()).append(") ---\n")
                .append("1. add film/music\n")
                .append("2. rating film/music\n")
                .append("3. my ratings (films)\n")
                .append("4. my ratings (musics)\n")
                .append("5. see my recommendations (film)\n")
                .append("6. see my recommendations (musics)\n")
                .append("0. off\n")
                .append("your option: ");

        System.out.print(sb);

        int choice = -1;
        try{
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Invalidate option. Try again.");
            return;
        }
        switch (choice) {
            case 1:
                handleRegisterItem();
                break;
            case 2:
                handleRateItem();
                break;
            case 3:
                handleListMyRatings(TypeItem.FILM);
                break;
            case 4:
                handleListMyRatings(TypeItem.MUSIC);
                break;
            case 5:
                handleGetRecommendations(TypeItem.FILM);
                break;
            case 6:
                handleGetRecommendations(TypeItem.MUSIC);
                break;
            case 0:
                System.out.println("see you, " + currentUser.getUserName() + "!");
                currentUser = null;
                break;
            default:
                System.out.println("Invalidate option. Try again.");
        }
    }

    private void handleGetRecommendations(TypeItem type) {
        try{
            List<Item> recommendations = recommendationService.getRecommendationsByCategory(currentUser.getId(), type);

            if(recommendations.isEmpty()) {
                System.out.println("Recommendations not found in " + type + ".");
                return;
            }

            for (int i = 0; i < recommendations.size(); i++) {
                Item item = recommendations.get(i);
                System.out.println(" " + (i+1) + ". " + item.getItemName() + " (Category: " + item.getCategory().getName() + ")");

            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleListMyRatings(TypeItem type) {
        System.out.println("\n>>> Your ratings: " + type.name() + " <<<");
        try {
            List<Rating> myRatings = ratingService.findUserRatings(currentUser.getId());
            int count = 0;
            for (Rating r : myRatings) {
                if (r.getItem().getType() == type) {
                    System.out.println("  - " + r.getItem().getItemName() + " (Rating: " + r.getRating() + "; Category: " + r.getItem().getCategory().getName() + ")");
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("Rating not found.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void handleRateItem() {
        System.out.println("\n--- Rating Item ---");
        System.out.print("Enter the NAME (exact title) of the item you want to evaluate: ");
        String title;
        int nota;
        try {
            title = scanner.nextLine();

            Item item = itemService.findItemByTitle(title);

            System.out.print("Enter your rating (1 to 5) for '" + item.getItemName() + "': ");
            nota = Integer.parseInt(scanner.nextLine());

            ratingService.ratingItem(currentUser.getId(), item.getId(), nota);
            System.out.println("Thank you! Rating saved successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Error: The rating must be a number (1-5).");
        } catch (Exception e) {
            System.out.println("Error saving rating:" + e.getMessage());
        }
    }

    private void handleRegisterItem() {
        System.out.println("\n--- Add new item ---");
        try {
            System.out.print("Is the item a (1) Movie or (2) Music? ");
            int typeChoice = Integer.parseInt(scanner.nextLine());

            System.out.print("Title: ");
            String title = scanner.nextLine();
            System.out.print("Category (e.g., Action, Rock, Drama, Pop): ");
            String categoryName = scanner.nextLine();

            if (typeChoice == 1) {
                System.out.print("Duration (in minutes): ");
                int duration = Integer.parseInt(scanner.nextLine());
                System.out.print("Director: ");
                String director = scanner.nextLine();

                Film newFilm = itemService.registerNewFilm(title, categoryName, duration, director);
                System.out.println("Success! '" + newFilm.getItemName() + "' film (ID: " + newFilm.getId() + ") created.");

            } else if (typeChoice == 2) {
                System.out.print("Artist: ");
                String artist = scanner.nextLine();
                System.out.print("Album: ");
                String album = scanner.nextLine();

                Music newMusic = itemService.registerNewMusic(title, categoryName, artist, album);
                System.out.println("Success! '" + newMusic.getItemName() + "' music (ID: " + newMusic.getId() + ") created.");

            } else {
                System.out.println("Error: Invalidate option. Try again.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: The type and duration should be numbers.");
        } catch (Exception e) {
            System.out.println("Error registering item: " + e.getMessage());
        }
    }
}
