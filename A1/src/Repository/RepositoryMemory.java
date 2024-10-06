package Repository;
import Model.*;
public class RepositoryMemory implements Repository {
    private Tree[] trees;
    private int size;
    public RepositoryMemory() {
        trees = new Tree[20];
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
    public void addTree(Tree tree){
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
}
