package model.adt;

import model.value.IValue;

import java.util.HashMap;

public interface IMyHeap {
    public int getCurrentKey();

    public IValue getValue(Integer key);

    public void updateValue(Integer key, IValue value);

    boolean contains(Integer key);

    public Integer allocate(IValue value);

    public String toString();

    public HashMap<Integer, IValue> getElements();

    public void setContent( HashMap<Integer, IValue> elems);
}

