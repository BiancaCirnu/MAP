package source.model.expression;

import source.exception.MyException;
import source.model.adt.IMyDictionary;
import source.model.adt.IMyHeap;
import source.model.type.IType;
import source.model.value.IValue;

public class VariableExpression implements IExpression {
    private final String variable;
    public VariableExpression(String variable) {
        this.variable = variable;
    }
    public IValue evaluate(IMyDictionary<String, IValue> symbolTable, IMyHeap heap) throws MyException {
        return symbolTable.getValue(variable);
    }

    @Override
    public IType typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        return typeEnvironment.getValue(variable);
    }

    public String toString() {
        return variable;
    }
}
