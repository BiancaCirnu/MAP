package model.modelExceptions;

import exception.MyException;

public class ElementExistsException extends MyException {
    public ElementExistsException() {
        super();
    }
    public ElementExistsException(String message) {
        super(message);
    }
}
