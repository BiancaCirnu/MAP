package model.modelExceptions;

import exception.MyException;

public class ValueHasWrongTypeException extends MyException {
    public ValueHasWrongTypeException(String message) {
        super(message);
    }
    public ValueHasWrongTypeException() { super();}
}
