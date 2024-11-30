package controller;

import exception.MyException;
import model.adt.IMyDictionary;
import model.adt.IMyStack;
import model.expression.*;
import model.programState.ProgramState;
import model.statement.*;
import model.type.*;
import model.value.*;
import repository.Repository;

import java.util.HashMap;

public class Controller {
    Repository repository;
    boolean displayFlag;

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

    public void allSteps() {
        try {
            ProgramState state = repository.getCurrentProgramState();
            repository.logPrgStateExec(state);
            while (!(state.getExecutionStack().isEmpty())) {
                oneStep(state);
                if (displayFlag)
                    displayCurrentProgramState();
                state.getHeap().setContent(garbageCollector(state.getSymbolTable(), state.getHeap().getElements()));
                repository.logPrgStateExec(state);
            }
        } catch (MyException e) {
            System.out.println(e.getMessage() + '\n');
        }

    }

    public void displayAllStates() {
        for (ProgramState state : repository.getProgramStateList())
            System.out.println(state.toString() + "\n__________________________________\n");

    }

    public ProgramState currentProgramState() {
        return repository.getCurrentProgramState();
    }

    public void setDisplayFlag() {
        displayFlag = true;
    }

    public void clearDisplayFlag() {
        displayFlag = false;
    }

    public void displayCurrentProgramState() {
        System.out.println(repository.getCurrentProgramState().toString() + "\n__________________________________\n");

    }

    public void addState(IStatement statement) {
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
        IStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new ArithmeticExpression("+", new ValueExpression(new IntValue(2)), new
                                ArithmeticExpression("*", new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5))))),
                                new CompoundStatement(new AssignStatement("b", new ArithmeticExpression("+", new VariableExpression("a"), new ValueExpression(new
                                        IntValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        addState(ex2);
        allSteps();
    }

    public void thirdProgram() {
        IStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BoolType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(new AssignStatement("a", new RelationalExpression(new ValueExpression(new IntValue(2)), RelationalOperator.GREATER, new ValueExpression(new IntValue(1)))),
                                new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignStatement("v", new ValueExpression(new
                                        IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
        addState(ex3);
        allSteps();
    }

    public void fourthProgram() {
        // Error 1
        IStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("a", new IntType()), new CompoundStatement(new VariableDeclarationStatement("b", new BoolType()), new AssignStatement("a", new VariableExpression("b"))));
        addState(ex4);
        allSteps();
    }

    public void fifthProgram() {
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

    public HashMap<Integer, IValue> garbageCollector(IMyDictionary<String, IValue> symTable,  HashMap<Integer, IValue> heap){
        for (IValue val : symTable.getValues().values()) {
            if (val instanceof RefValue) {
                int address = ((RefValue) val).getAddress();
                if (heap.containsKey(address)) {
                    heap.put(address, heap.get(address));
                }

                if(val.getType() instanceof RefValue) {
                    if(heap.containsKey(address)) {
                        IValue value = heap.get(address);
                        while(value instanceof RefValue) {
                            int address2 = ((RefValue) value).getAddress();
                            if(heap.containsKey(address2)) {
                                heap.put(address2, heap.get(address2));
                            }
                            value = heap.get(address2);
                        }
                    }
                }
            }
        }
        return heap;
    }

    public void sixthProgram() {
        IStatement ex = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntType())), new CompoundStatement(
                new Allocate2Statement("v", new ValueExpression(new IntValue())), new CompoundStatement(
                new WriteHeap2Statement("v", new ValueExpression(new IntValue(20))), new CompoundStatement(
                new VariableDeclarationStatement("a", new RefType(new RefType(new IntType()))), new CompoundStatement(
                new Allocate2Statement("a", new VariableExpression("v")), new CompoundStatement(
                new WriteHeap2Statement("a", new VariableExpression("v")), new CompoundStatement(
                new PrintStatement(new VariableExpression("a")), new PrintStatement(new VariableExpression("v")
        ))))))));
        addState(ex);
        allSteps();
    }
    public void seventhProgram()
    {
        IStatement ex = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement(
                        new AllocateStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(
                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                new CompoundStatement(
                                        new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                        new PrintStatement(new ArithmeticExpression("+", new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5))))
                                )
                        )
                )
        );
        addState(ex);
        allSteps();
    }
    public void eightProgram() {
        IStatement ex = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(
                        new AssignStatement("v", new ValueExpression(new IntValue(4))),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(new VariableExpression("v"), RelationalOperator.GREATER, new ValueExpression(new IntValue(0))),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignStatement("v", new ArithmeticExpression("-", new VariableExpression("v"), new ValueExpression(new IntValue(1))))
                                        )
                                ),
                                new PrintStatement(new VariableExpression("v"))
                        )
                )
        );
        addState(ex);
        allSteps();
    }
}
