package Exceptions;

public class ArrayIsFullException extends Exception {
    public ArrayIsFullException() {
        super("Array is full");
    }
    public ArrayIsFullException(String message) {
        super(message);
    }
}
