package domain;

import javax.persistence.*;

@Entity
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    protected String itemName;
    @Column(nullable = false)
    protected TypeItem type;

    public Item() {}

    public void registerItem(String itemName, TypeItem type) {
        this.itemName = itemName;
        if(type.equals(TypeItem.FILME) || type.equals(TypeItem.MUSICA)) {
            this.type = type;
        } else {
            System.out.println("Erro ao cadastrar " + type + ". Tipo não existe no sistema.");
        }
    }
}
