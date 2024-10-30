package model.expression;

import exception.MyException;
import model.adt.IMyDictionary;
import model.value.IValue;

public class VariableExpression implements IExpression {
    private String variable;
    public VariableExpression(String variable) {
        this.variable = variable;
    }
    public IValue evaluate(IMyDictionary<String, IValue> symbolTable) throws MyException {
        return symbolTable.getValue(variable);
    }
    public String toString() {
        return variable;
    }
}
