package model.statement;

import exception.MyException;
import model.programState.ProgramState;

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

}
