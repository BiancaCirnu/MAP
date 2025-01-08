package source;

import source.view.TextMenu;
import source.view.command.*;
public class Main {

    public static void main(String[] args){
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "Exit") );
        menu.addCommand(new RunCommand("1", "Run program 1"));
        menu.addCommand(new RunCommand("2", "Run program 2"));
        menu.addCommand(new RunCommand("3", "Run program 3"));
        menu.addCommand(new RunCommand("4", "Run program 4"));
        menu.addCommand(new RunCommand("5", "Run program 5"));
        menu.addCommand(new RunCommand("6", "Run program 6"));
        menu.addCommand(new RunCommand("7", "Run program 7"));
        menu.addCommand(new RunCommand("8", "Run program 8"));
        menu.addCommand(new RunCommand("9", "Run program 9")); 
        menu.addCommand(new RunCommand("10", "Run program 10"));
        menu.addCommand(new RunCommand("11", "Run program 11"));
        menu.addCommand(new RunCommand("12", "Run program 12"));
        menu.addCommand(new RunCommand("13", "Run program 13"));
        menu.addCommand(new RunCommand("14", "Run program 14"));
        menu.show();
    }
}