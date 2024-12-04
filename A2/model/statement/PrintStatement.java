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
        IValue res = expression.evaluate(state.getSymbolTable(), state.getHeap());
        state.getOutput().add(res.toString());
        return null;
    }
    public String toString() {
        return "print("+expression.toString()+")";
    }
}
