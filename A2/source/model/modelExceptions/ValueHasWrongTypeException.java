package source.model.modelExceptions;

import source.exception.MyException;

public class ValueHasWrongTypeException extends MyException {
    public ValueHasWrongTypeException(String message) {
        super(message);
    }
    public ValueHasWrongTypeException() { super();}
}
