package model.adt;

import model.modelExceptions.ElementDoesNotExistException;
import model.modelExceptions.ElementExistsException;

import java.util.Map;

public interface IMyDictionary<Key, Value> {
    public void insert(Key key, Value value);
    public Value getValue(Key key) throws ElementDoesNotExistException;
    public boolean contains(Key key);
    public void remove(Key key) throws ElementDoesNotExistException;
    public String toString();
    public IMyDictionary<Key, Value> deepCopy();
    public Map<Key, Value> getValues();
}
