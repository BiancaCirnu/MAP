//package view;
//
//import controller.Controller;
//import exception.MyException;
//import model.expression.ArithmeticExpression;
//import model.expression.ValueExpression;
//import model.expression.VariableExpression;
//import model.statement.*;
//import model.type.BoolType;
//import model.type.IntType;
//import model.type.StringType;
//import model.value.BoolValue;
//import model.value.IntValue;
//import model.value.StringValue;
//import repository.Repository;
//
//import java.util.Scanner;
//
//public class View {
//    Controller controller;
//    public View() {
//        controller = new Controller();
//    }
//    private void printMenu(){
//        System.out.println("Program 1:");
//        System.out.println("int v; v=2; Print(v)");
//        System.out.println("Program 2:");
//        System.out.println("int a;int b;a=2+3*5;b=a+1");
//        System.out.println("Program 3:");
//        System.out.println("bool a; int v; a = true; (If a Then v=2 Else v=3); Print(v)");
//
//        System.out.println("Choose option");
//        System.out.println("1. Run first program");
//        System.out.println("2. Run second program");
//        System.out.println("3. Run third program");
//        System.out.println("4. Run fourth program");
//        System.out.println("5. Run fifth program");
//        System.out.println("6. Run all programs");
//
//        System.out.println("7. Set display flag to true");
//        System.out.println("8. Set display flag to false");
//        System.out.println("9. Quit program");
//    }
//    public void start() {
//
//        printMenu();
//        Scanner scanner = new Scanner(System.in);
//        int option = scanner.nextInt();
//        while (option != 9 )
//        {
//            switch (option) {
//                case 1:
//                    firstProgram();
//                    break;
//                case 2:
//                    secondProgram();
//                    break;
//                case 3:
//                    thirdProgram();
//                    break;
//                case 4:
//                    fourthProgram();
//                    break;
//                case 5:
//                    fifthProgram();
//                    break;
//                case 6:
//                    allPrograms();
//                    break;
//                case 7:
//                    controller.setDisplayFlag();
//                    break;
//                case 8:
//                    controller.clearDisplayFlag();
//                    break;
//                default:
//                    System.out.println("Invalid option");
//                    break;
//            }
//            printMenu();
//            scanner = new Scanner(System.in);
//            option = scanner.nextInt();
//        }
//    }
//
//    public void firstProgram() {
//        Repository repository = new Repository("log1.txt");
//        controller = new Controller(repository);
//        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
//        controller.addState(ex1);
//        controller.allSteps();
//        if(controller.getRepository().getProgramStateList().get(0).getExecutionStack().isEmpty())
//            System.out.println("Program 1 result:\n" + controller.currentProgramState().toString());
//    }
//
//    public void secondProgram() {
//        Repository repository = new Repository("log2.txt");
//        controller = new Controller(repository);
//        IStatement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),
//                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
//                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
//                                ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
//                                new CompoundStatement(new AssignStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new ValueExpression(new
//                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))) );
//        controller.addState(ex2);
//        controller.allSteps();
//        if(controller.getRepository().g().getExecutionStack().isEmpty())
//            System.out.println("Program 2 result:\n" + controller.currentProgramState().toString());
//    }
//
//    public void thirdProgram() {
//        Repository repository = new Repository("log3.txt");
//        controller = new Controller(repository);
//        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
//                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
//                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
//                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ValueExpression(new
//                                        IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
//                                        VariableExpression("v"))))));
//        controller.addState(ex3);
//        controller.allSteps();
//        if(controller.getRepository().getCurrentProgramState().getExecutionStack().isEmpty())
//            System.out.println("Program 3 result:\n" + controller.currentProgramState().toString());
//    }
//
//    public void fourthProgram(){
//        // Error 1
//        Repository repository = new Repository("log4.txt");
//        controller = new Controller(repository);
//        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()), new CompoundStatement(new VariableDeclarationStatement("b", new BoolType()), new AssignStatement("a", new VariableExpression("b"))));
//        controller.addState(ex4);
//        controller.allSteps();
//        if(controller.getRepository().getCurrentProgramState().getExecutionStack().isEmpty())
//            System.out.println("Program 4 result:\n" + controller.currentProgramState().toString());
//    }
//
//    public void fifthProgram(){
//        Repository repository = new Repository("log5.txt");
//        controller = new Controller(repository);
//        IStatement ex5 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
//                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
//                new CompoundStatement(new OpenRFileStatement(new VariableExpression("varf")),
//                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
//                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
//                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
//                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
//                        new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
//                                new CloseRFileStatement(new VariableExpression("varf"))))))))));
//        controller.addState(ex5);
//        controller.allSteps();
//    }
//
//    public void allPrograms() {
//        firstProgram();
//        secondProgram();
//        thirdProgram();
//        fourthProgram();
//        fifthProgram();
//    }
//
//}
//
