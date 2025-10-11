package dao;

import domain.Item;
import util.BinarySearchTree;

import javax.persistence.EntityManager;
import java.util.List;

public class ItemDAO extends AbastractDAO<Item, Long>{
    private final BinarySearchTree<Item> itemBinaryTree;

    public ItemDAO(EntityManager em) {
        super(em);
        this.itemBinaryTree = new BinarySearchTree<>();
        loadItens();
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
    public void save(Item entity) {
        super.save(entity);
        this.itemBinaryTree.insert(entity);
    }

    @Override
    public void remove(Item entity) {
        super.remove(entity);
        this.itemBinaryTree.delete(entity);
    }

    @Override
    public void att(Item entity){
        // para att é preciso remover o estado antigo e inserir o novo
        Item itemTree = this.itemBinaryTree.search(entity);
        if(itemTree != null) {
            this.itemBinaryTree.delete(itemTree);
        }

        super.att(entity);
        this.itemBinaryTree.insert(entity);
    }
}
