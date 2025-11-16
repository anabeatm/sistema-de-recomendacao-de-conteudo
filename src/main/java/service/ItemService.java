package service;

import dao.ItemDAO;
import domain.*;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import java.util.List;

public class ItemService {
    private final ItemDAO itemDAO;
    private final CategoryService categoryService;


    public ItemService(ItemDAO itemDAO, CategoryService categoryService) {
        this.itemDAO = itemDAO;
        this.categoryService = categoryService;
    }

    public Film registerNewFilm(String title, String categoryName, int duration, String director) {
        Category category = categoryService.findOrCreateCategory(categoryName);

        Film newFilm = new Film(title, TypeItem.FILM, category, duration, director);
        itemDAO.save(newFilm);
        return newFilm;
    }

    public Music registerNewMusic(String title, String categoryName, String artist, String album) {
        Category category = categoryService.findOrCreateCategory(categoryName);

        Music newMusic = new Music(title, TypeItem.MUSIC, category, artist,album);
        itemDAO.save(newMusic);
        return newMusic;
    }

    public Item findItemByID(Long id){
        Item item = itemDAO.searchByID(id);
        if(item == null) throw new RuntimeException("Error: Item with ID "+id+" not found.");

        return item;
    }

    public List<Item> listAllItens() {
        return itemDAO.listAll();
    }

    public Item findItemByTitle(String title) {
        EntityManager em = itemDAO.getEm();
        try {
            TypedQuery<Item> query = em.createQuery(
                    "SELECT i FROM Item i WHERE LOWER(i.item_name) = LOWER(:title)",
                    Item.class
            );
            query.setParameter("title", title);

            List<Item> items = query.getResultList();

            if (items.isEmpty()) {

                throw new NoResultException();
            }

            return items.get(0);

        } catch (NoResultException e) {
            throw new RuntimeException("Error: item with name '" + title + "' not found.");
        }
    }
}
