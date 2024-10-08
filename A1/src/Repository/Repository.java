package Repository;
import Model.*;
import Exceptions.*;
public interface Repository {
    public int getSize();
    public Tree[] getTrees();
    public Tree getTree(int treeIndex);
    public void addTree(Tree tree) throws ArrayIsFullException;
    public void removeTree(int treeIndex);
    public void undoLastOperation();
    public int getNumberOfTreesOlderThanThreeYears();
}