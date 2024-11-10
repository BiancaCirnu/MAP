package model.modelExceptions;

import exception.MyException;

public class FileIsNotOpenException extends MyException {
    public FileIsNotOpenException(){
        super();
    }
    public FileIsNotOpenException(String message){
        super(message);
    }
}
