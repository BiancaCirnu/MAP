package source.model.expression;

import source.model.adt.IMyDictionary;
import source.exception.MyException;
import source.model.adt.IMyHeap;
import source.model.type.IType;
import source.model.value.IValue;

public interface IExpression {
    IValue evaluate(IMyDictionary<String, IValue> symbolTable,  IMyHeap heap) throws MyException;
    IType typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException;
}
