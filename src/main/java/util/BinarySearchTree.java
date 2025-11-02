package util;

import java.lang.Comparable;

// BinaryTree will be used as a "cache" to store the items, as its search processing is faster compared to the database

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

    public void insert(E value) {
        insert(value, root);
    }

    private Node<E> insert(E value, Node<E> currentRoot) {
        if(isNull(currentRoot)) {
            currentRoot = new Node<>(value);
        } else if (value.compareTo(currentRoot.value)<0) {
            currentRoot.left = insert(value, currentRoot.left);
        } else if(value.compareTo(currentRoot.value)>0) {
            currentRoot.right = insert(value, currentRoot.right);
        }
        return currentRoot;
    }

    public void delete(E value) {
        root = delete(value, root);
    }

    private Node<E> delete(E value, Node<E> currentRoot) {
        if(isNull(currentRoot)) return null;

        if(value.compareTo(currentRoot.value) < 0) {
            currentRoot.left = delete(value, currentRoot.left);
        } else if (value.compareTo(currentRoot.value)>0) {
            currentRoot.right = delete(value, currentRoot.right);
        } else {
            boolean hasLeft = currentRoot.left != null;
            boolean hasRight = currentRoot.right != null;

            if(!hasLeft && !hasRight) {
                return null;
            }
            if(!hasRight) return currentRoot.left;
            else if (!hasLeft) return currentRoot.right;

            Node<E> minRight = min(currentRoot.right);
            currentRoot.value = minRight.value;
            currentRoot.right = delete(minRight.value, currentRoot.right);
        }

        return currentRoot;
    }

    private Node<E> min(Node<E> currentRoot) {
        Node<E> aux = currentRoot;
        while(aux.left!=null) {
            aux = aux.left;
        }
        return aux;
    }

    public E search(E value) {
        return search(value, root);
    }

    private E search(E value, Node<E> currentRoot) {
        if(isNull(currentRoot)) return null;

        int compare = value.compareTo(currentRoot.value);

        if(compare == 0) {
            return currentRoot.value;
        } else if (compare<0) {
            return search(value, currentRoot.left);
        } else {
            return search(value, currentRoot.right);
        }
    }

    private boolean isNull(Node<E> root) {
        if(root == null) return true;

        return false;
    }

}
