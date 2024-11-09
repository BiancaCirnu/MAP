package repository;

import exception.MyException;
import model.programState.ProgramState;
import repository.repositoryExceptions.listIsEmptyException;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    List<ProgramState> programStateList;
    int currentProgramState;
    public Repository() {
        programStateList = new ArrayList<>();
        currentProgramState = 0;
    }
    public void addProgramState(ProgramState programState) {
        programStateList.add(programState);
    }

    public List<ProgramState> getProgramStateList() {
        return programStateList;
    }
    public ProgramState getCurrentProgramState() {
        try{
            if (programStateList.isEmpty())
                throw new listIsEmptyException("the repo is empty");
        }
        catch (MyException e){
            System.err.println(e.getMessage());
        }
        return programStateList.get(currentProgramState);
    }
    public void logPrgStateExec(){
        //TODO
    }

}
