package service;

import dao.CategoryDAO;
import dao.ItemDAO;
import domain.*;

import java.util.List;

public class ItemService {
    private final ItemDAO itemDAO;
    private final CategoryDAO categoryDAO;

    public ItemService(ItemDAO itemDAO, CategoryDAO categoryDAO) {
        this.itemDAO = itemDAO;
        this.categoryDAO = categoryDAO;
    }

    public Film registerNewFilm(String title, Long categoryID, int duration, String director) {
        Category category = categoryDAO.searchByID(categoryID);
        if(category == null) throw new RuntimeException("Error: Category with ID "+categoryID+" not found.");

        Film newFilm = new Film(title, TypeItem.FILM, category, duration, director);
        itemDAO.save(newFilm);
        return newFilm;
    }

    public Music registerNewMusic(String title, Long categoryID, String artist, String album) {
        Category category = categoryDAO.searchByID(categoryID);
        if(category == null) throw new RuntimeException("Error: Category with ID "+categoryID+" not found.");

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


    // TODO mudar esses métodos para outra classe tipo util

//    public Item findByFilmTitle(String title){
//        return this.itemBinaryTree.search(new Film(title, null, null, 0, null));
//    }
//
//    public Item findByMusicTitle(String title){
//        return this.itemBinaryTree.search(new Music(title, null, null, null, null));
//    }
}
