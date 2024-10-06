package Controller;
import Model.*;
import Repository.*;
public interface Controller {
    public Repository getRepository();
    public Tree[] getTreesOlderThanThreeYears();
    public Tree[] getTrees();
    public void addTree(String type, int age);
    public void removeTree(int index);


}
