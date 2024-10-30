package model.adt;

import java.util.ArrayList;
import java.util.List;

public class MyList<Elem> implements IMyList<Elem> {
    private List<Elem> list;
    public MyList() {
        list = new ArrayList<Elem>();
    }
    public MyList(List<Elem> list) {
        this.list = list;
    }
    public List<Elem> getList() {
        return list;
    }
    @Override
    public void add(Elem elem) {
        list.add(elem);
    }
    @Override
    public int getSize(){
        return list.size();
    }
    public String toString(){
        String str = "";
        for(Elem elem : list){
            str += elem.toString()+'\n';
        }
        return str;
    }
    public IMyList<Elem> deepCopy(){
        return new MyList<Elem>(list);
    }
}
