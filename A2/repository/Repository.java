package repository;

import exception.MyException;
import model.programState.ProgramState;
import repository.repositoryExceptions.listIsEmptyException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    List<ProgramState> programStateList;
    int currentProgramState;
    String logfile;
    public Repository(String logfile) {
        programStateList = new ArrayList<>();
        currentProgramState = 0;
        this.logfile = logfile;
    }
    public void addProgramState(ProgramState programState) {
        programStateList.add(programState);
    }

    public List<ProgramState> getProgramStateList() {
        return programStateList;
    }
    public String getLogFile() {
        return logfile;
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
    public void logPrgStateExec(ProgramState state) throws MyException {
        try {
            PrintWriter log = new PrintWriter(new BufferedWriter(new FileWriter(logfile, true)));
            log.println(state.toString());
            log.close();
        } catch (IOException e) {
            throw new MyException("Can't create print writer");
        }
    }

}
