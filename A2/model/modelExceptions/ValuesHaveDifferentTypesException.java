package model.modelExceptions;

import exception.MyException;

public class ValuesHaveDifferentTypesException extends MyException {
    public ValuesHaveDifferentTypesException() {
        super();
    }
    public ValuesHaveDifferentTypesException(String message) {
        super(message);
    }
}
