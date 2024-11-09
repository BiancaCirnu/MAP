package model.modelExceptions;

import exception.MyException;

public class OpenFileException extends MyException {
    public OpenFileException(){
        super();
    }
    public OpenFileException(String m){
        super(m);
    }
}
