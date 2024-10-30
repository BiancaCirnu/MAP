package model.statement;

import model.programState.ProgramState;

public class NopStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState state)
    {
        return state;
    }

    @Override
    public String toString()
    {
        return "NopStatements";
    }
}
