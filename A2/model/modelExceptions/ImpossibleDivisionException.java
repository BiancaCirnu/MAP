package model.modelExceptions;

import exception.MyException;

public class ImpossibleDivisionException extends MyException {
    public ImpossibleDivisionException() {
        super();
    }
    public ImpossibleDivisionException(String message) {
        super(message);
    }
}
