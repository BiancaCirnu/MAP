package model.expression;

import model.adt.IMyDictionary;
import exception.MyException;
import model.value.IValue;

public interface IExpression {
    IValue evaluate(IMyDictionary<String, IValue> symbolTable) throws MyException;
}
