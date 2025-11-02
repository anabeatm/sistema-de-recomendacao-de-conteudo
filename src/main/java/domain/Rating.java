package domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = false)
    private int rating; // 1 to 5

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date rating_date;


    public Rating(){}

    public Rating(User user, Item item, int rating) {
        if(rating < 1 || rating > 5) {
            throw new IllegalArgumentException("The rating should be in a range between 1 and 5.");
        }
        this.user = user;
        this.item = item;
        this.rating = rating;
        this.rating_date = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Date getRating_date() {
        return rating_date;
    }

    public void setRating_date(Date rating_date) {
        this.rating_date = rating_date;
    }

    public boolean isHighRating() {
        return this.rating >= 4;
    }

    public boolean ifLowRating() {
        return this.rating <= 2;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "user=" + user.getUserName() +
                ", item=" + item.getItemName() +
                ", rating=" + getRating() +
                ", date=" + getRating_date() +
                '}';
    }
}
