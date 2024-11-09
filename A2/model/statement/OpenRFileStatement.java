package model.statement;

import exception.MyException;
import model.adt.IMyStack;
import model.expression.IExpression;
import model.modelExceptions.ValueHasWrongTypeException;
import model.programState.ProgramState;
import model.type.StringType;

public class OpenRFileStatement implements IStatement{
    IExpression expression;
    public OpenRFileStatement(IExpression expression) {
        this.expression = expression;
    }
    public ProgramState execute(ProgramState state) throws MyException {
        IMyStack<IStatement> exeSt = state.getExecutionStack();
        IStatement statement = exeSt.pop();
        //TODO
        return state;

    }

}
