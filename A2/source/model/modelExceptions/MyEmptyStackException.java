package source.model.modelExceptions;

import source.exception.MyException;

public class MyEmptyStackException extends MyException {
    public MyEmptyStackException() {
        super();
    }
    public MyEmptyStackException(String message) {
        super(message);
    }
}
