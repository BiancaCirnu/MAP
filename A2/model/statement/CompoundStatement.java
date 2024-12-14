package model.statement;

import exception.MyException;
import model.adt.IMyDictionary;
import model.adt.MyDictionary;
import model.programState.ProgramState;
import model.type.IType;

public class CompoundStatement implements IStatement {
    private IStatement firstStatement, secondStatement;
    public CompoundStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }
    public ProgramState execute(ProgramState state) throws MyException {
        state.getExecutionStack().push(secondStatement);
        state.getExecutionStack().push(firstStatement);
        return null;
    }
    public String toString(){
        return  firstStatement.toString() + "; " + secondStatement.toString() ;
    }

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        IMyDictionary<String, IType> typeEnvironment1 = firstStatement.typeCheck(typeEnvironment);
        IMyDictionary<String, IType> typeEnvironment2 = secondStatement.typeCheck(typeEnvironment1);
        return  typeEnvironment2;
    }

}
