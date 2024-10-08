package Repository;
import Exceptions.ArrayIsFullException;
import Model.*;
import Exceptions.*;
public class RepositoryMemory implements Repository {
    private static int ARRAY_SIZE = 20;
    private Tree[] trees;
    private int size;
    public RepositoryMemory() {
        trees = new Tree[ARRAY_SIZE];
        size = 0;
    }
    @Override
    public int getSize(){
        return size;
    }
    public int getNumberOfTreesOlderThanThreeYears(){
        int number = 0;
        for(int i = 0; i<this.size; i++){
            if(trees[i].isOlderThanThreeYears())
                number++;
        }
        return number;
    }
    @Override
    public Tree[] getTrees() {
        return trees;
    }
    @Override
    public Tree getTree(int treeIndex) {
        return  trees[treeIndex];
    }
    @Override
    public void addTree(Tree tree) throws ArrayIsFullException {
        if(this.size == ARRAY_SIZE){
            throw new ArrayIsFullException();
        }
        trees[size] = tree;
        size++;
    }
    @Override
    public void removeTree(int treeIndex) {
        for(int i = treeIndex; i < size-1; i++){
            trees[i] = trees[i+1];
        }
        size-=1;
    }
    @Override
    public void undoLastOperation() {

    }
}
