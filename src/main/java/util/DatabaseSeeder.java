package util;

import com.github.javafaker.Faker;
import domain.*;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DatabaseSeeder {
    private static final int BATCH = 50;

    public static void run() {
        EntityManager em = null;
        Faker faker = new Faker(new Locale("pt-BR"));
        Random random = new Random();

//        try{
//            em = JPAUtil.getEntityManager();
//
//            int QNTD_USERS = 10000;
//            int QNTD_FILMS = 5000;
//            int QNTD_MUSICS = 5000;
//            int QTND_RATINGS = 80000;
//            // TODO
//            List<Category> categories = seedCategories
//        }
    }

    private static List<Category> seedCategories(EntityManager em, Faker faker) {
        List<Category> created = new ArrayList<>();
        String[] catNames = {"Ficção Científica", "Rock", "Ação", "Pop", "Animação", "Comédia", "Drama", "Terror",
                "Romance", "Fantasia", "Clássica", "Eletrônica", "Hip-Hop", "Indie", "Documentário", "Mistério",
                "Suspense", "Guerra", "Faroeste", "Musical"};

        em.getTransaction().begin();

        for(String name : catNames) {
            Category cat = new Category(name);
            em.persist(cat);
            created.add(cat);
        }
        em.getTransaction().commit();
        return created;
    }

    private static List<User> seedUsers(EntityManager em, Faker faker, int count) {
        List<User> created = new ArrayList<>(count);

        em.getTransaction().begin();

        for(int i = 1; i < count; i++) {
            User user = new User(faker.name().username(), faker.internet().emailAddress());
            em.persist(user);
            created.add(user);

            if(i % BATCH == 0) {
                em.flush();
                em.clear();
            }
        }

        em.getTransaction().commit();
        return created;
    }

    private static List<Item> seedItems(EntityManager em, Faker faker, List<Category> categories, int filmCount, int musicCount) {
        List<Item> created = new ArrayList<>(filmCount + musicCount);
        Random random = new Random();

        em.getTransaction().begin();

        for(int i = 1; i <= filmCount; i++) {
            Category cat = categories.get(random.nextInt(categories.size()));
            Film film = new Film(
                    faker.book().title(),
                    TypeItem.FILM,
                    cat,
                    random.nextInt(120) + 60, // 60 to 180 min
                    faker.name().fullName()
            );
            em.persist(film);
            created.add(film);

            if(i % BATCH == 0) {
                em.flush();
                em.clear();
            }
        }

        for(int i = 1; i < musicCount; i++) {
            Category cat = categories.get(random.nextInt(categories.size()));

            Music music = new Music(
                    faker.book().title() + " Song",
                    TypeItem.MUSIC,
                    cat,
                    faker.rockBand().name(),
                    faker.book().genre() + " Album"
            );

            em.persist(music);
            created.add(music);

            if(i % BATCH == 0) {
                em.flush();
                em.clear();
            }
        }

        em.getTransaction().commit();
        return created;
    }

    private static void seedRatings(EntityManager em, List<User> users, List<Item> items, int count, Random random) {
        em.getTransaction().begin();

        for (int i = 1; i < count; i++) {
            User user = users.get(random.nextInt(users.size()));
            Item item = items.get(random.nextInt(items.size()));
            int rating = random.nextInt(5) + 1; // 1 to 5

            Rating ran = new Rating(user, item, rating);
            em.persist(ran);

            if(i % BATCH == 0) {
                em.flush();
                em.clear();
            }
        }
        em.getTransaction().commit();
    }
}
