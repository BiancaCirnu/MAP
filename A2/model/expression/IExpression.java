package model.expression;

import model.adt.IMyDictionary;
import exception.MyException;
import model.adt.IMyHeap;
import model.type.IType;
import model.value.IValue;

public interface IExpression {
    IValue evaluate(IMyDictionary<String, IValue> symbolTable,  IMyHeap heap) throws MyException;
}
