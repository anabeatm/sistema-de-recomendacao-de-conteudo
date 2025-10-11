package domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item implements Comparable<Item>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    protected String item_name;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    protected TypeItem type;

    public Item(String item_name, TypeItem type) {
        this.item_name = item_name;
        if(type.equals(TypeItem.FILM) || type.equals(TypeItem.MUSIC)) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Error: " + item_name + ". Must be a movie or song.");
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return item_name;
    }

    public void setItemName(String item_name) {
        this.item_name = item_name;
    }

    public TypeItem getType() {
        return type;
    }

    public void setType(TypeItem type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Item {" +
                "id=" + getId() +
                ", title=" + getItemName() +
                ", type=" + getType() +
                '}';
    }

    @Override
    public int compareTo(Item other) {
        return this.item_name.compareTo(other.getItemName());
    }

// preciso mesmo desses métodos?
//    TODO perguntar ao professor e implementar se necessário
//  @Override
//  public boolean equals(Object o) {}

//    @Override
//    public int hashCode() {
//        return getClass().hashCode();
//    }
}
