package domain;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {
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
        if(type.equals(TypeItem.FILME) || type.equals(TypeItem.MUSICA)) {
            this.type = type;
        } else {
            System.out.println("Erro ao cadastrar: " + item_name + ". Precisa ser um filme ou música.");
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
}
