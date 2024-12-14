package model.statement;

import exception.MyException;
import model.adt.IMyDictionary;
import model.adt.MyDictionary;
import model.programState.ProgramState;
import model.type.IType;

public interface IStatement {
    public ProgramState execute(ProgramState state) throws MyException;
    public String toString();
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException;

}
