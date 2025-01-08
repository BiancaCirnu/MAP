package source.model.modelExceptions;

import source.exception.MyException;

public class FileIsNotOpenException extends MyException {
    public FileIsNotOpenException(){
        super();
    }
    public FileIsNotOpenException(String message){
        super(message);
    }
}
