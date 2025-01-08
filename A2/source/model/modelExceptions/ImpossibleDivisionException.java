package source.model.modelExceptions;

import source.exception.MyException;

public class ImpossibleDivisionException extends MyException {
    public ImpossibleDivisionException() {
        super();
    }
    public ImpossibleDivisionException(String message) {
        super(message);
    }
}
