package model.adt;

import model.modelExceptions.MyEmptyStackException;

import java.util.EmptyStackException;

public interface IMyStack<Elem> {
    public void push(Elem elem);
    public Elem pop() throws MyEmptyStackException;
    public int getSize();
    public boolean isEmpty();
    public IMyStack<Elem> deepCopy();
}
