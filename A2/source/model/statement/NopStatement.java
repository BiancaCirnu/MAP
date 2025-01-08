package source.model.statement;

import source.exception.MyException;
import source.model.adt.IMyDictionary;
import source.model.programState.ProgramState;
import source.model.type.IType;

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
