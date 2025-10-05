package domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "item_id")
public class Film extends Item {
    @Column(length = 20, nullable = false)
    private String category;
    @Column(nullable = false)
    private int timeInMinutes;
    @Column(length = 50, nullable = false)
    private String director;

    public Film(String item_name, TypeItem type, String category, int timeInMinutes, String director) {
        super(item_name, type);
        this.category = category;
        this.timeInMinutes = timeInMinutes;
        this.director = director;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getFormattedDuration() {
        if(timeInMinutes <= 0) return "N/A";

        long hours = timeInMinutes / 60;
        long minutes = timeInMinutes % 60;

        return String.format("%dh %02dm", hours, minutes);
    }

    @Override
    public String toString() {
        return "Film {" +
                "id=" + getId() +
                ", title=" + getItemName() +
                ", director=" + getDirector() +
                '}';
    }


}
