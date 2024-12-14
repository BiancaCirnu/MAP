package model.adt;

import model.modelExceptions.ElementDoesNotExistException;
import model.modelExceptions.ElementExistsException;

import java.util.HashMap;
import java.util.Map;

public class MyDictionary<Key, Value> implements IMyDictionary<Key, Value> {
    private Map<Key, Value> map;
    public MyDictionary() {
        map = new HashMap<Key, Value>();
    }
    public MyDictionary(Map<Key, Value> map) {
        this.map = new HashMap<Key, Value>(map);
    }

    public Map<Key, Value> getValues(){
        return map;
    }


    @Override
    public boolean contains(Key key) {
        return map.containsKey(key);
    }

    @Override
    public Value getValue(Key key) throws ElementDoesNotExistException {
        if(contains(key))
            return map.get(key);
        else throw new ElementDoesNotExistException("Key not found");
    }

    @Override
    public void insert(Key key, Value value){
       map.put(key, value);
    }

    @Override
    public void remove(Key key) throws ElementDoesNotExistException {
        if(!contains(key))
            throw new ElementDoesNotExistException("Key not found");
        map.remove(key);
    }

    @Override
    public String toString() {
        String str = "";
        for (Map.Entry<Key, Value> entry : map.entrySet()) {
            str += "{" +entry.getKey() + ", "+entry.getValue() + "}\n";
        }
        return str;
    }
    public IMyDictionary<Key, Value> deepCopy() {
        MyDictionary<Key, Value> copy = new MyDictionary<>();
        for (Map.Entry<Key, Value> entry : map.entrySet()) {
           copy.insert(entry.getKey(), entry.getValue());
        }
        return copy;
    }
}
