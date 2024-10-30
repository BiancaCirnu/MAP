package repository;

import model.programState.ProgramState;

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
    public ProgramState getCurrentProgramState() {
        return programStateList.get(currentProgramState);
    }
    public List<ProgramState> getProgramStateList() {
        return programStateList;
    }
}
