package model.statement;

import model.programState.ProgramState;

public class NopStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState state)
    {
        return null;
    }

    @Override
    public String toString()
    {
        return "NopStatements";
    }
}
