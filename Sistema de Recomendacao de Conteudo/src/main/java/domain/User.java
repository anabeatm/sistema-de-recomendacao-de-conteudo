package domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String userName;
    @Column(length = 50, nullable = false)
    private String userEmail;

    private List<Rating> ratingFilms; // lista de filmes avaliados
    private List<Rating> ratingMusics; // lista de músicas avaliadas
    private List<Item> favorites; // lista de favoritos

    public User() {}

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public List<Rating> getRatingFilms() {
        return ratingFilms;
    }

    public List<Rating> getRatingMusics() {
        return ratingMusics;
    }

    public void register(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

    // TODO o método precisa verificar se o item é um filme ou música, além de chegar o limite da regra de négocio
    public void addToFavorites(Item item){}

    public void removeFromFavorites(Item item) {}

    // TODO getters que filtram a lista geral favorities
    public List<Rating> getFavoritesFilms(){}
    public List<Rating> getFavoritesMusics(){}
}
