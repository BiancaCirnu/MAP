package model.adt;

public class BTNode<T> {
    public T elem;
    public BTNode<T> left;
    public BTNode<T> right;
    public BTNode(T elem, BTNode<T> left, BTNode<T> right){
        this.elem = elem;
        this.left = left;
        this.right = right;
    }
    public BTNode(T elem){
        this.elem = elem;
        left = null;
        right = null;
    }

}
