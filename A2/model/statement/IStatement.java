package model.statement;

import exception.MyException;
import model.programState.ProgramState;

public interface IStatement {
    public ProgramState execute(ProgramState state) throws MyException;
    public String toString();
}
