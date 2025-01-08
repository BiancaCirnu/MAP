package source.model.modelExceptions;

import source.exception.MyException;

public class ElementExistsException extends MyException {
    public ElementExistsException() {
        super();
    }
    public ElementExistsException(String message) {
        super(message);
    }
}
