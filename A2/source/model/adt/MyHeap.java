package source.model.adt;

import source.model.value.IValue;

import java.util.HashMap;

public class MyHeap implements IMyHeap {
    static Integer keyCount = 0;
    HashMap<Integer, IValue> elements;
    public MyHeap(){
        elements = new HashMap<>();
        keyCount = 0;
    }
    public int getCurrentKey(){
        return keyCount;
    }
    public void setContent( HashMap<Integer, IValue> elems){
        elements = elems;
    }

    @Override
    public void deallocate(Integer v) {
        elements.remove(v);
    }

    public IValue getValue(Integer key){
        return elements.get(key);
    }
    public HashMap<Integer, IValue> getElements(){
        return elements;
    }
    public void updateValue(Integer key, IValue value){
        elements.put(key, value);
    }
    public boolean contains(Integer key){
        return elements.containsKey(key);
    }

    public Integer allocate(IValue value){
        keyCount += 1;
        elements.put(keyCount, value);
        return keyCount;
    }

    public String toString(){
        String s = "";
        for(Integer key: elements.keySet())
            s = s.concat(key.toString()+": "+elements.get(key).toString()+'\n');
        return s;
    }

}
