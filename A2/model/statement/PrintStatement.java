package model.statement;

import exception.MyException;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.value.IValue;

public class PrintStatement implements IStatement {
    private IExpression expression;
    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }
    public ProgramState execute(ProgramState state) throws MyException {
        IValue res = expression.evaluate(state.getSymbolTable());
        state.getOutput().add(res.toString());
        return state;
    }
    public String toString() {
        return "print("+expression.toString()+")";
    }
}
