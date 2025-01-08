package source.repository.repositoryExceptions;

import source.exception.MyException;

public class listIsEmptyException extends MyException {
    public listIsEmptyException(String message) {
        super(message);
    }
    public listIsEmptyException() {
        super();
    }
}
