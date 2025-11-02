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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    protected Category category;

    public Item(){}

    public Item(String item_name, TypeItem type, Category category) {
        this.item_name = item_name;
        this.type = type;
        this.category = category;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id != null && id.equals(item.id);
    }

}
