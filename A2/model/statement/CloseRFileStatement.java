package model.statement;

import model.expression.IExpression;
import model.programState.ProgramState;

public class CloseRFileStatement implements IStatement{
    IExpression expression;
    public CloseRFileStatement(IExpression expression) {
        this.expression = expression;
    }
    public ProgramState execute(ProgramState state) {
        //TODO
        return state;
    }
}
