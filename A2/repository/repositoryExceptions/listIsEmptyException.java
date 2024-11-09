package repository.repositoryExceptions;

import exception.MyException;

public class listIsEmptyException extends MyException {
    public listIsEmptyException(String message) {
        super(message);
    }
    public listIsEmptyException() {
        super();
    }
}
