package controller;

import exception.MyException;
import model.adt.IMyStack;
import model.expression.ArithmeticExpression;
import model.expression.ValueExpression;
import model.expression.VariableExpression;
import model.programState.ProgramState;
import model.statement.*;
import model.type.BoolType;
import model.type.IntType;
import model.type.StringType;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.StringValue;
import repository.Repository;

public class Controller {
    Repository repository;
    boolean displayFlag ;
    public Controller() {
        repository = new Repository("test.in");
        displayFlag = true;
    }
    public Controller(Repository programs) {
        this.repository = programs;
        displayFlag = false;
    }
    public ProgramState oneStep(ProgramState state) throws MyException {
        IMyStack<IStatement> stack = state.getExecutionStack();
        if (stack.isEmpty()) {
            throw new MyException("Stack is empty");
        }
        IStatement statement = stack.pop();
        return statement.execute(state);
    }
    public void allSteps()  {
        try
        {
            ProgramState state = repository.getCurrentProgramState();
            repository.logPrgStateExec(state);
            while (!(state.getExecutionStack().isEmpty())) {
                oneStep(state);
                if (displayFlag)
                    displayCurrentProgramState();
                repository.logPrgStateExec(state);
            }
        }
        catch (MyException e)
        {
            System.out.println(e.getMessage() + '\n');
        }

    }
    public void displayAllStates(){
        for (ProgramState state: repository.getProgramStateList())
            System.out.println(state.toString() + "\n__________________________________\n");

    }
    public ProgramState currentProgramState(){
        return repository.getCurrentProgramState();
    }

    public void setDisplayFlag(){
        displayFlag = true;
    }
    public void clearDisplayFlag(){
        displayFlag = false;
    }

    public void displayCurrentProgramState(){
        System.out.println(repository.getCurrentProgramState().toString() + "\n__________________________________\n");

    }
    public void addState(IStatement statement){
        repository.addProgramState(new ProgramState(statement));
    }

    public Repository getRepository() {
        return repository;
    }

    //HARDCODED EXAMPLES

    public void firstProgram() {
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
        addState(ex1);
        allSteps();
    }

    public void secondProgram() {
        IStatement ex2 = new CompoundStatement( new VariableDeclarationStatement("a",new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression("+",new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression("*",new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b",new ArithmeticExpression("+",new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))) );
        addState(ex2);
        allSteps();
    }

    public void thirdProgram() {
        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a",new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ValueExpression(new
                                        IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
        addState(ex3);
        allSteps();
    }

    public void fourthProgram(){
        // Error 1
        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()), new CompoundStatement(new VariableDeclarationStatement("b", new BoolType()), new AssignStatement("a", new VariableExpression("b"))));
        addState(ex4);
        allSteps();
    }

    public void fifthProgram(){
        IStatement ex5 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenRFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseRFileStatement(new VariableExpression("varf"))))))))));
        addState(ex5);
        allSteps();
    }
}
