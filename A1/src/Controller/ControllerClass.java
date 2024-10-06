package Controller;

import Model.*;
import Repository.*;

public class ControllerClass implements Controller {
    Repository repository;
    public ControllerClass(){
        this.repository = new RepositoryMemory();
    }
    @Override
    public Repository getRepository() {
        return repository;
    }
    @Override
    public Tree[] getTrees(){
        return repository.getTrees();
    }
    @Override
    public Tree[] getTreesOlderThanThreeYears(){
        Tree[] oldTrees = new Tree[20];
        int oldTreesSize = 0;
        for(int i= 0;i< repository.getSize();i++){
            if (repository.getTree(i).isOlderThanThreeYears())
            {
                oldTrees[oldTreesSize] = repository.getTree(i);
                oldTreesSize++;
            }
        }
        return oldTrees;
    }
    @Override
    public void addTree(String type, int age){
        if(type.equals("Apple"))
            repository.addTree(new Apple(age));
        else if (type.equals("Pear"))
            repository.addTree(new Pear(age));
        else if (type.equals("Cherry"))
            repository.addTree(new Cherry(age));
    }
    @Override
    public void removeTree(int index){
        repository.removeTree(index);
    }
}
