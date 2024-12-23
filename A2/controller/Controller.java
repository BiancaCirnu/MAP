package controller;

import exception.MyException;
import model.adt.IMyDictionary;
import model.adt.IMyHeap;
import model.adt.IMyStack;
import model.adt.MyDictionary;
import model.expression.*;
import model.programState.ProgramState;
import model.statement.*;
import model.type.*;
import model.value.*;
import repository.Repository;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    Repository repository;
    boolean displayFlag;
    ExecutorService executor;
    public Controller() {
        repository = new Repository("test.in");
        displayFlag = true;
    }

    public Controller(Repository programs) {
        this.repository = programs;
        displayFlag = false;
    }
    public void oneStepForAllPrograms(List<ProgramState> programStates)
    {

            programStates.forEach(programState -> {
                try {
                    repository.logPrgStateExec(programState);
                } catch (MyException e) {
                    System.out.println(e.getMessage());
                }
            });
            List<Callable<ProgramState>> callList = programStates.stream().map((ProgramState p
            ) -> (Callable<ProgramState>) (() -> {
                return p.oneStep();
            })).collect(Collectors.toList());
        List<ProgramState> newProgramList = null;
        try {
            newProgramList = executor.invokeAll(callList).stream().map(future -> {
                try {
                    return future.get();
                } catch (ExecutionException | InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }).filter(p -> p != null).collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        programStates.addAll(newProgramList);
            programStates.forEach(programState -> {
                try {
                    repository.logPrgStateExec(programState);
                } catch (MyException e) {
                    System.out.println(e.getMessage());
                }
            });
            repository.setProgramStateList(programStates);


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

    public void garbageCollector() {
        List<Integer> activeAddresses = repository.getProgramStateList().stream().flatMap(e -> getAddrFromSymTable(e).stream()).toList();
        IMyHeap heap = repository.getProgramStateList().get(0).getHeap();
        List<Integer> addresses = heap.getElements().keySet().stream().filter(e -> !activeAddresses.contains(e)).toList();
        addresses.forEach(e -> {heap.deallocate(e); });
    }

    public List<Integer> getAddrFromSymTable(ProgramState state) {
        Collection<IValue> symTableValues = state.getSymbolTable().getValues().values();

        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> ((RefValue) v).getAddress())
                .collect(Collectors.toList());
    }



    void allSteps(){
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> programList = removeCompletedPrograms(repository.getProgramStateList());
        while(programList.size()>0){
            garbageCollector();
            oneStepForAllPrograms(programList);
            programList = removeCompletedPrograms(repository.getProgramStateList());
        }
        executor.shutdownNow();
        repository.setProgramStateList(programList);
    }

//    public void allSteps() {
//        try {
//            ProgramState state = repository.getProgramStateList().get(0);
//            repository.logPrgStateExec(state);
//            while (!(state.getExecutionStack().isEmpty())) {
//                state.oneStep();
//                if (displayFlag)
//                    displayCurrentProgramState();
//                state.getHeap().setContent(garbageCollector(state.getSymbolTable(), state.getHeap().getElements()));
//                repository.logPrgStateExec(state);
//            }
//        } catch (MyException e) {
//            System.out.println(e.getMessage() + '\n');
//        }
//
//    }

    public void displayAllStates() {
        for (ProgramState state : repository.getProgramStateList())
            System.out.println(state.toString() + "\n__________________________________\n");

    }

    public ProgramState currentProgramState() {
        return repository.getProgramStateList().get(0);
    }

    public void setDisplayFlag() {
        displayFlag = true;
    }

    public void clearDisplayFlag() {
        displayFlag = false;
    }

    public void displayCurrentProgramStates() {
        for(var p: repository.getProgramStateList())
            System.out.println(p.toString() + "\n__________________________________\n");

    }

    public void addState(IStatement statement) {
        repository.addProgramState(new ProgramState(statement));
    }

    public Repository getRepository() {
        return repository;
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programStatesList)
    {
        return programStatesList.stream().filter(programState -> programState.isNotCompleted()).collect(Collectors.toList());
    }
    //HARDCODED EXAMPLES

    public void firstProgram() {
        IStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(2))), new PrintStatement(new VariableExpression("v"))));
        try{
            ex1.typeCheck(new MyDictionary<String, IType>());
        }catch (MyException e)
        {
            System.out.println("Didn't pass the type checker");
        }
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
        try{
            ex2.typeCheck(new MyDictionary<String, IType>());
        }catch (MyException e)
        {
            System.out.println("Didn't pass the type checker");
        }
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
        try{
            ex3.typeCheck(new MyDictionary<String, IType>());
        }catch (MyException e)
        {
            System.out.println("Didn't pass the type checker");
        }
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
        try{
            ex5.typeCheck(new MyDictionary<String, IType>());
        }catch (MyException e)
        {
            System.out.println("Didn't pass the type checker");
        }
        addState(ex5);
        allSteps();
    }

    public void sixthProgram() {
        IStatement ex = new CompoundStatement(
                new VariableDeclarationStatement("v", new RefType(new IntType())), // Declare 'v' as a reference to an int
                new CompoundStatement(
                        new AllocateStatement("v", new ValueExpression(new IntValue(20))), // Allocate memory for 'v' with value 20
                        new CompoundStatement(
                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))), // Print the value read from the heap at 'v'
                                new CompoundStatement(
                                        new WriteHeapStatement("v", new ValueExpression(new IntValue(30))), // Write 30 into the heap location referenced by 'v'
                                        new PrintStatement(new ArithmeticExpression(
                                                "+",
                                                new ReadHeapExpression(new VariableExpression("v")), // Read the value at 'v'
                                                new ValueExpression(new IntValue(5)) // Add 5
                                        ))
                                )
                        )
                )
        );
        try{
            ex.typeCheck(new MyDictionary<String, IType>());
        }catch (MyException e)
        {
            System.out.println("Didn't pass the type checker");
        }
        addState(ex); // Add this statement to the program's state
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
        try{
            ex.typeCheck(new MyDictionary<String, IType>());
        }catch (MyException e)
        {
            System.out.println("Didn't pass the type checker");
        }
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
    public void ninthProgram(){
        IStatement ex = new CompoundStatement(new VariableDeclarationStatement("v", new RefType(new IntType())),
                new CompoundStatement( new AllocateStatement("v", new ValueExpression(new IntValue(20))),
                new CompoundStatement( new VariableDeclarationStatement("a",new RefType(new RefType(new IntType()))), new CompoundStatement(new AllocateStatement("a",new VariableExpression("v")),
                new CompoundStatement(new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))), new PrintStatement(new ArithmeticExpression("+", new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5))))
                )))));
        try{
            ex.typeCheck(new MyDictionary<String, IType>());
        }catch (MyException e)
        {
            System.out.println("Didn't pass the type checker");
        }
        addState(ex);
        allSteps();
    }
    public void tenthProgram() {
        IStatement ex = new CompoundStatement(
                new VariableDeclarationStatement("v", new IntType()), // Declare variable 'v'
                new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new IntType())), // Declare reference 'a'
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(10))), // Assign 'v' = 10
                new CompoundStatement(new AllocateStatement("a", new ValueExpression(new IntValue(22))), // Allocate memory for 'a' with value 22
                new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeapStatement("a", new ValueExpression(new IntValue(30))), // Modify heap at 'a'
                new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(32))), // Assign 'v' = 32
                new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))) // Print value in heap at 'a'
                )))), new CompoundStatement(new PrintStatement(new VariableExpression("v")), // Print 'v' in main thread
                new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))) // Print value in heap at 'a' in main thread
                ))))));
        try{
            ex.typeCheck(new MyDictionary<String, IType>());
        }catch (MyException e)
        {
            System.out.println("Didn't pass the type checker");
        }

        addState(ex);
        allSteps();
    }
    public void eleventhProgram() {
        IStatement ex =  new CompoundStatement(new VariableDeclarationStatement("a", new RefType(new IntType())), new CompoundStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompoundStatement(new AllocateStatement("a", new ValueExpression(new IntValue(10))), new CompoundStatement(new ForkStatement(new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(20))),
                        new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeapStatement("a", new ValueExpression(new IntValue(40))), new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))),
                                new PrintStatement(new VariableExpression("v"))))), new CompoundStatement(new AssignStatement("v", new ValueExpression(new IntValue(30))), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))))))));


        addState(ex);
        allSteps();
    }

    public void Program12(){
        IStatement ex = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()), new CompoundStatement(new AssignStatement("varf", new ValueExpression(new StringValue("test.in"))),
                new CompoundStatement(new OpenRFileStatement(new VariableExpression("varf")), new CompoundStatement(new ForkStatement(new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()), new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"), new PrintStatement(new VariableExpression("varc"))))),
                        new CompoundStatement(new VariableDeclarationStatement("varc", new IntType()), new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"), new CompoundStatement(new PrintStatement(new VariableExpression("varc")),new CloseRFileStatement(new VariableExpression("varf")))))))));

        try{
            ex.typeCheck(new MyDictionary<String, IType>());
        }catch (MyException e)
        {
            System.out.println("Didn't pass the type checker");
        }
        addState(ex);
        allSteps();
    }

}

// 6 7 10 