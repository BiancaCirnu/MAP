package source.view;
import source.exception.MyException;
import source.view.command.Command;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class TextMenu {
    private Map<String, Command> commands;
    public TextMenu(){
        commands = new HashMap<>();
    }
    public void addCommand(Command c){
        commands.put(c.getKey(), c);
    }
    private void printMenu(){

        for(Command c: commands.values()){
            String line= String.format("%4s: %s", c.getKey(), c.getDescription());
            System.out.println(line);
        }
    }
    public void checkStates(){
        for(var k: commands.keySet())
        {

            if(k != "0")
                continue;
            try {
                commands.get(k).checkCommandState();
            } catch (MyException exception) {
                StringBuilder s = new StringBuilder();
                s.append("Error in program ");
                s.append(k);
                System.out.println(s);
                System.out.println(exception.getMessage());
            }
        }
    }
    public void show(){
        Scanner scanner = new Scanner(System.in);
        checkStates();
        while(true){
            printMenu();
            System.out.println("Input the option: ");
            String key = scanner.nextLine();
            Command c = commands.get(key);
            if (c == null)
            {
                System.out.println("Invalid option");
                continue;
            }
            try{
                c.execute();
            }catch (MyException e)
            {
             System.out.println(" ");
            }
        }
    }

}
