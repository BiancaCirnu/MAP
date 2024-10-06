package Repository;
import Model.*;
public interface Repository {
    public int getSize();
    public Tree[] getTrees();
    public Tree getTree(int treeIndex);
    public void addTree(Tree tree);
    public void removeTree(int treeIndex);
    public int getNumberOfTreesOlderThanThreeYears();
}
