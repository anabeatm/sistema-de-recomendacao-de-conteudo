package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "item_id")
public class Music extends Item {

    @Column(length = 100, nullable = false)
    private String artist;

    @Column(length = 100, nullable = false)
    private String album;

    public Music(){}

    public Music (String item_name, TypeItem type, Category category, String artist, String album) {
        super(item_name, type, category);
        this.artist = artist;
        this.album = album;
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
