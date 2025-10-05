package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "item_id")
public class Music extends Item {
    @Column(length = 20, nullable = false)
    private String category;
    @Column(length = 100, nullable = false)
    private String artist;
    @Column(length = 100, nullable = false)
    private String album;

    public Music (String item_name, TypeItem type, String category, String artist, String album) {
        super(item_name, type);
        this.category = category;
        this.artist = artist;
        this.album = album;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Music {" +
                "id=" + getId() +
                ", title=" + getItemName() +
                ", artist=" + getArtist() +
                '}';
    }
}
