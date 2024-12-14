package model.statement;

import exception.MyException;
import model.adt.IMyDictionary;
import model.programState.ProgramState;
import model.type.IType;

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

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        return typeEnvironment;
    }
}
