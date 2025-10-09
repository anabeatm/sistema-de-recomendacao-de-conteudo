package util;

import org.jetbrains.annotations.NotNull;

import java.lang.Comparable;

public class BinarySearchTree<E extends Comparable<E>>{
    private Node<E> root;

    private static class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;

        public Node(E value) {
            this.value = value;
        }
    }

    public void add(E value) {
        add(value, root);
    }

    private Node add(E value, Node<E> currentRoot) {
        if(isNull(currentRoot)) {
            currentRoot = new Node(value);
        } else if (value.compareTo(currentRoot.value)<0) {
            currentRoot.left = add(value, currentRoot.left);
        } else if(value.compareTo(currentRoot.value)>0) {
            currentRoot.right = add(value, currentRoot.right);
        }
        return currentRoot;
    }

    private boolean isNull(Node<E> root) {
        if(root == null) return true;

        return false;
    }
// TODO implementar métodos de remoção e busca
}
