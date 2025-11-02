package domain;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    public Category(String name){
        this.name = name;
    }

    public Category(){}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
