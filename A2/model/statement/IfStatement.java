package model.statement;

import exception.MyException;
import model.expression.IExpression;
import model.modelExceptions.ValueHasWrongTypeException;
import model.programState.ProgramState;
import model.type.BoolType;
import model.value.BoolValue;

public class IfStatement implements IStatement {
    IExpression condition;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(IExpression condition, IStatement thenStatement, IStatement elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public ProgramState execute(ProgramState state) throws MyException {
        if(!(condition.evaluate(state.getSymbolTable(), state.getHeap()).getType()).equals(new BoolType()))
            throw new ValueHasWrongTypeException();
        BoolValue res = (BoolValue) condition.evaluate(state.getSymbolTable(), state.getHeap());
        if (res.equals(new BoolValue(true)))
            state.getExecutionStack().push(thenStatement);
        else
            state.getExecutionStack().push(elseStatement);
        return state;
    }
    public String toString() {
        return "if " + condition.toString() + " then " + thenStatement.toString() + " else " + elseStatement.toString();
    }
}
