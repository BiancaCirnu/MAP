package view.command;

import exception.MyException;

public class ExitCommand extends Command{
    public ExitCommand(String key, String description){
        super(key, description);
    }

    public void execute(){
        System.exit(0);
    }

    @Override
    public void checkCommandState() throws MyException {

    }
}
