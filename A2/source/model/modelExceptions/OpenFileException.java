package source.model.modelExceptions;

import source.exception.MyException;

public class OpenFileException extends MyException {
    public OpenFileException(){
        super();
    }
    public OpenFileException(String m){
        super(m);
    }
}
