package controller;

import exception.MyException;
import model.adt.IMyStack;
import model.programState.ProgramState;
import model.statement.IStatement;
import repository.Repository;

public class Controller {
    Repository repository;
    boolean displayFlag ;
    public Controller() {
        repository = new Repository();
        displayFlag = true;
    }
    public ProgramState oneStep(ProgramState state) throws MyException {
        IMyStack<IStatement> stack = state.getExecutionStack();
        if (stack.isEmpty()) {
            throw new MyException("Stack is empty");
        }
        IStatement statement = stack.pop();
        return statement.execute(state);
    }
    public void allStates(){
        ProgramState state = repository.getCurrentProgramState();
        while (!(state.getExecutionStack().isEmpty())){
            try{
                oneStep(state);
                if(displayFlag)
                    displayCurrentProgramState();
            }
            catch (MyException e){
                break;
            }
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
        repository = new Repository();
        repository.addProgramState(new ProgramState(statement));
    }
}
