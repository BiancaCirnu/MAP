package source.model.statement;

import source.exception.MyException;
import source.model.adt.IMyDictionary;
import source.model.programState.ProgramState;
import source.model.type.IType;

public interface IStatement {
    public ProgramState execute(ProgramState state) throws MyException;
    public String toString();
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException;

}
