package model.modelExceptions;

import exception.MyException;

public class MyEmptyStackException extends MyException {
    public MyEmptyStackException() {
        super();
    }
    public MyEmptyStackException(String message) {
        super(message);
    }
}
