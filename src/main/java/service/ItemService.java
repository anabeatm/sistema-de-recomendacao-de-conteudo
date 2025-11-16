package service;

import dao.ItemDAO;
import domain.*;

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

    private Item findByFilmTitle(String title){
        Film filmSearch = new Film(title, TypeItem.FILM, null, 0, "");
        return this.itemDAO.getItemBinaryTree().search(filmSearch);
    }

    private Item findByMusicTitle(String title){
        Music musicSearch = new Music(title, TypeItem.MUSIC, null, "", "");
        return this.itemDAO.getItemBinaryTree().search(musicSearch);
    }

    public Item findItemByTitle(String title) {
        Item item = findByFilmTitle(title);
        if(item == null) item = findByMusicTitle(title);

        if(item == null) throw new RuntimeException("Error: item with name '"+title+"' not found.");
        return item;
    }
}
