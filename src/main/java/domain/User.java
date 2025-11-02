package domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String user_name;

    @Column(length = 50, nullable = false)
    private String user_email;

    // relacionamento um para muitos; "user" é o dono do relacionamento;
    // cascade todas as operações feitas no <<User>> serão refletidas nos <<Rating>>
    // orphanRemoval se um <<Rating>> for removido da lista ratings do <<User>>, ele também será deletado do banco
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

//  private List<Item> favorites; TODO voltar aqui depois

    public User(String user_name, String user_email) {
        this.user_name = user_name;
        this.user_email = user_email;
    }

    public User(){}

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return user_name;
    }

    public String getUserEmail() {
        return user_email;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

}
