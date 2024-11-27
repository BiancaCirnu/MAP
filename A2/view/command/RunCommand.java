package view.command;
import controller.Controller;
import repository.Repository;

public class RunCommand extends Command{
    //private Controller controller;
    public RunCommand(String key, String description){
        super(key, description);
    }
    public void execute(){
        Controller c;
        switch(getKey()){
            case "1":
                c = new Controller(new Repository("log1.txt"));
                c.firstProgram();
                break;
            case "2":
                c = new Controller(new Repository("log2.txt"));
                c.secondProgram();
                break;
            case "3":
                c = new Controller(new Repository("log3.txt"));
                c.thirdProgram();
                break;
            case "4":
                c = new Controller(new Repository("log4.txt"));
                c.fourthProgram();
                break;
            case "5":
                c = new Controller(new Repository("log5.txt"));
                c.fifthProgram();
                break;
            case "6":
                c = new Controller(new Repository("log6.txt"));
                c.sixthProgram();
            case "7":
                c = new Controller(new Repository("log7.txt"));
                c.seventhProgram();
            case "8":
                c = new Controller(new Repository("log8.txt"));
                c.eightProgram();
        }
    }
}
