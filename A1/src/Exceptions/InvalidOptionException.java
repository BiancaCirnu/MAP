package Exceptions;

public class InvalidOptionException extends Exception{

    public InvalidOptionException(){
        super("Invalid Option");
    }
    public InvalidOptionException(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
