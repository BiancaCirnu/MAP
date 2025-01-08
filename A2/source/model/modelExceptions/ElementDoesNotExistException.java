package source.model.modelExceptions;

import source.exception.MyException;

public class ElementDoesNotExistException extends MyException {
    public ElementDoesNotExistException(String message) {
        super(message);
    }
    public ElementDoesNotExistException() { super();}
}
