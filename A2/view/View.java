package view;

import controller.Controller;
import model.expression.ArithmeticExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.value.BoolValue;
import model.value.IntValue;

import java.util.Scanner;

public class View {
    Controller controller;
    public View() {
        controller = new Controller();
    }
    private void printMenu(){
        System.out.println("Program 1:");
        System.out.println("int v; v=2; Print(v)");
        System.out.println("Program 2:");
        System.out.println("int a;int b;a=2+3*5;b=a+1");
        System.out.println("Program 3:");
        System.out.println("bool a; int v; a = true; (If a Then v=2 Else v=3); Print(v)");

        System.out.println("Choose option");
        System.out.println("1. Run first program");
        System.out.println("2. Run second program");
        System.out.println("3. Run third program");
        System.out.println("4. Run all programs");

        System.out.println("5. Set display flag to true");
        System.out.println("6. Set display flag to false");
        System.out.println("7. Quit program");
    }
    public void start() {

        printMenu();

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        while (option != 7)
        {
            switch (option) {
                case 1:
                    firstProgram();
                    break;
                case 2:
                    secondProgram();
                    break;
                case 3:
                    thirdProgram();
                    break;
                case 4:
                    allPrograms();
                    break;
                case 5:
                    controller.setDisplayFlag();
                    break;
                case 6:
                    controller.clearDisplayFlag();
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            printMenu();
            scanner = new Scanner(System.in);
            option = scanner.nextInt();
        }
    }
    public void firstProgram() {
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
        controller.addState(ex1);
        controller.allStates();
        System.out.println("Program 1 result:\n" + controller.currentProgramState().toString());
    }

    public void secondProgram() {
        IStatement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))) );
        controller.addState(ex2);
        controller.allStates();
        System.out.println("Program 2 result:\n" + controller.currentProgramState().toString());
    }

    public void thirdProgram() {
        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ValueExpression(new
                                        IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
        controller.addState(ex3);
        controller.allStates();
        System.out.println("Program 3 result:\n" + controller.currentProgramState().toString());
    }
    public void allPrograms() {
        firstProgram();
        secondProgram();
        thirdProgram();
    }

}

