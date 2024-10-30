package model.modelExceptions;

import exception.MyException;

public class ElementDoesNotExistException extends MyException {
    public ElementDoesNotExistException(String message) {
        super(message);
    }
    public ElementDoesNotExistException() { super();}
}
