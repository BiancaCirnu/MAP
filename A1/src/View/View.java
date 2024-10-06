package View;
import Controller.*;
import Model.*;
import Repository.*;
import Exceptions.*;

import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
public class View {
    Controller controller;
    public View() {
        this.controller = new ControllerClass();
    }
    private void printMenu(){
        System.out.println("Choose an option:");
        System.out.println("1. Add tree");
        System.out.println("2. Show trees");
        System.out.println("3. Show trees older than three years old");
        System.out.println("4. Exit");
    }
    private void printTreeMenu(){
        System.out.println("Choose an option:");
        System.out.println("1. Apple");
        System.out.println("2. Pear");
        System.out.println("3. Cherry");
    }
    private void addTree(){
        printTreeMenu();
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        while(choice <1 || choice > 3){
            System.out.println("Invalid choice. Try again.");
            choice = scanner.nextInt();
        }
        System.out.println("Introduce age:");
        int age = scanner.nextInt();
        String type = "";
        switch(choice){
            case 1:
                type = "Apple";
                break;
            case 2:
                type = "Pear";
                break;
            case 3:
                type = "Cherry";
                break;
            default:
                break;
        }
        controller.addTree(type, age);
    }
    private void showTrees(){
        for(int i = 0;i<controller.getRepository().getSize();i++)
        {
            System.out.println(controller.getTrees()[i]);
            System.out.println("Do you want to remove this tree? \n 1. yes \n 2. no \n 3. stop showing trees");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.next();
            if (choice.equalsIgnoreCase("1"))
                controller.removeTree(i);
            else if (choice.equalsIgnoreCase("3"))
                break;

        }
    }
    private int chooseOption(){
        Scanner scanner = new Scanner(System.in);
        String choice = "0";
        int choiceNum = 0;
        while(choice.equals("0"))
        {
            try {
                choice = scanner.nextLine();
                if(!Objects.equals(choice, "1") && !Objects.equals(choice, "2") && !Objects.equals(choice, "3") && !Objects.equals(choice, "4"))
                    throw  new InvalidOptionException();
                switch(choice){
                    case "1":
                        choiceNum = 1;
                        break;
                    case "2":
                        choiceNum = 2;
                        break;
                    case "3":
                        choiceNum = 3;
                        break;
                    case "4":
                        choiceNum = 4;
                        break;
                    default:
                        break;
                }
            }
            catch (InvalidOptionException e) {
                System.out.println("Invalid choice. Option must be between 1 and 4");
            }
        }
        return choiceNum;
    }
    private void showTreesOlderThanThreeYears(){
        Tree[] oldTrees = controller.getTreesOlderThanThreeYears();
        int a;
        for(int i = 0;i<controller.getRepository().getNumberOfTreesOlderThanThreeYears(); i++) {
            System.out.println(oldTrees[i].toString());
        }
    }
    public void start(){
        printMenu();
        Scanner scanner = new Scanner(System.in);

        int choiceNum = chooseOption();
        while(choiceNum != 4)
        {
            switch (choiceNum) {
                case 1:
                    addTree();
                    break;
                case 2:
                    showTrees();
                    break;
                case 3:
                    showTreesOlderThanThreeYears();
                    break;
            }
            printMenu();
            choiceNum = chooseOption();

        }
    }
}
