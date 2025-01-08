package source.model.adt;

import source.model.modelExceptions.MyEmptyStackException;

public interface IMyStack<Elem> {
    public void push(Elem elem);
    public Elem pop() throws MyEmptyStackException;
    public int getSize();
    public boolean isEmpty();
    public IMyStack<Elem> deepCopy();
}
