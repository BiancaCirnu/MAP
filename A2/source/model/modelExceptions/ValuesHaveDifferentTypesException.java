package source.model.modelExceptions;

import source.exception.MyException;

public class ValuesHaveDifferentTypesException extends MyException {
    public ValuesHaveDifferentTypesException() {
        super();
    }
    public ValuesHaveDifferentTypesException(String message) {
        super(message);
    }
}
