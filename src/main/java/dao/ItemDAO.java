package dao;

import domain.Item;
import util.BinarySearchTree;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

public class ItemDAO extends AbstractDAO<Item, Long> {
    private final BinarySearchTree<Item> itemBinaryTree;

    public ItemDAO(EntityManager em) {
        super(em);
        this.itemBinaryTree = new BinarySearchTree<>(); // stores <<Item>> objects in "cache"
        loadItens(); // loads items from the database and inserts them into the <<BinaryTree>>
    }

//  Binary Tree treats
    private void loadItens(){
        System.out.println("Loading itens...");
        List<Item> allItens = super.listAll();
        for(Item item : allItens) {
            this.itemBinaryTree.insert(item);
        }
        System.out.println("Full load.");
    }


    @Override
    public Item save(Item entity) {
        super.save(entity);
        if(entity.getId() != null) {
            this.itemBinaryTree.insert(entity);
        }
        return entity;
    }

    @Override
    public void remove(Item entity) {
        super.remove(entity);
        this.itemBinaryTree.delete(entity);
    }

    @Override
    public void update(Item entity){
        // para update é preciso remover o estado antigo e inserir o novo
        Item itemTree = this.itemBinaryTree.search(entity);
        if(itemTree != null) {
            this.itemBinaryTree.delete(itemTree);
        }

        super.update(entity);
        this.itemBinaryTree.insert(entity);
    }
}
