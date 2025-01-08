package source.view.command;
import source.controller.Controller;
import source.exception.MyException;
import source.repository.Repository;

public class RunCommand extends Command{
    private Controller controller;
    public RunCommand(String key, String description){
        super(key, description);
    }
    public void execute()throws MyException {
        switch(getKey()){
            case "1":
                controller = new Controller(new Repository("log1.txt"));
                controller.firstProgram();
                break;
            case "2":
                controller = new Controller(new Repository("log2.txt"));
                controller.secondProgram();
                break;
            case "3":
                controller = new Controller(new Repository("log3.txt"));
                controller.thirdProgram();
                break;
            case "4":
                controller = new Controller(new Repository("log4.txt"));
                controller.fourthProgram();
                break;
            case "5":
                controller = new Controller(new Repository("log5.txt"));
                controller.fifthProgram();
                break;
            case "6":
                controller = new Controller(new Repository("log6.txt"));
                controller.sixthProgram();
                break;
            case "7":
                controller = new Controller(new Repository("log7.txt"));
                controller.seventhProgram();
                break;
            case "8":
                controller = new Controller(new Repository("log8.txt"));
                controller.eightProgram();
                break;
            case "9":
                controller = new Controller(new Repository("log9.txt"));
                controller.ninthProgram();
                break;
            case "10":
                controller = new Controller(new Repository("log10.txt"));
                controller.tenthProgram();
                break;
            case "11":
                controller = new Controller(new Repository("log11.txt"));
                controller.eleventhProgram();
                break;
            case "12":
                controller = new Controller(new Repository("log12.txt"));
                controller.Program12();
                break;
            case "13":
                controller= new Controller(new Repository("log13.txt"));
                controller.Program13();
                break;
            case "14":
                controller= new Controller(new Repository("log14.txt"));
                controller.Program14();
                break;
        }
    }
    public void checkCommandState()throws MyException {
        switch (getKey()) {
            case "1":
                controller = new Controller(new Repository("log1.txt"), false);
                controller.firstProgram();
                break;
            case "2":
                controller = new Controller(new Repository("log2.txt"), false);
                controller.secondProgram();
                break;
            case "3":
                controller = new Controller(new Repository("log3.txt"), false);
                controller.thirdProgram();
                break;
            case "4":
                controller = new Controller(new Repository("log4.txt"), false);
                controller.fourthProgram();
                break;
            case "5":
                controller = new Controller(new Repository("log5.txt"), false);
                controller.fifthProgram();
                break;
            case "6":
                controller = new Controller(new Repository("log6.txt"), false);
                controller.sixthProgram();
                break;
            case "7":
                controller = new Controller(new Repository("log7.txt"), false);
                controller.seventhProgram();
                break;
            case "8":
                controller = new Controller(new Repository("log8.txt"), false);
                controller.eightProgram();
                break;
            case "9":
                controller = new Controller(new Repository("log9.txt"), false);
                controller.ninthProgram();
                break;
            case "10":
                controller = new Controller(new Repository("log10.txt"), false);
                controller.tenthProgram();
                break;
            case "11":
                controller = new Controller(new Repository("log11.txt"), false);
                controller.eleventhProgram();
                break;
            case "12":
                controller = new Controller(new Repository("log12.txt"), false);
                controller.Program12();
                break;
            case "13":
                controller = new Controller(new Repository("log13.txt"), false);
                controller.Program13();
                break;
            case "14":
                controller = new Controller(new Repository("log14.txt"), false);
                controller.Program14();
                break;
        }
    }
}
