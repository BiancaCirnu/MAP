package source.model.adt;


// Create a weird bt for printing the execution stack

public class MyBinaryTree<T> implements IMyBinaryTree<T> {
    public BTNode<T> head;
    public MyBinaryTree(){
        head = null;
    }
    @Override
    public void insert(T elem) {
        if(head == null)
        {
            head = new BTNode<T>(elem);
            return;
        }
        BTNode<T> currentNode = head;
        while(currentNode.right != null && currentNode.left != null)
            currentNode = currentNode.left;
        if(currentNode.right == null)
            currentNode.right = new BTNode<T>(elem);
        else
            currentNode.left = new BTNode<T>(elem);
    }
}
