package model.adt;

public interface IMyList<Elem> {
    public int getSize();
    public void add(Elem elem);
    public IMyList<Elem> deepCopy();
}
