package model.expression;

import exception.MyException;
import model.adt.IMyDictionary;
import model.adt.IMyHeap;
import model.modelExceptions.ElementDoesNotExistException;
import model.modelExceptions.ValueHasWrongTypeException;
import model.modelExceptions.ValuesHaveDifferentTypesException;
import model.type.IType;
import model.type.RefType;
import model.value.IValue;
import model.value.RefValue;

public class ReadHeapExpression implements IExpression{
    IExpression expression;
    public ReadHeapExpression(IExpression expression){
        this.expression = expression;
    }
    @Override
    public IValue evaluate(IMyDictionary<String, IValue> symbolTable, IMyHeap heap) throws MyException {
        IValue evaluatedExpression = expression.evaluate(symbolTable, heap);
        if(!(evaluatedExpression instanceof RefValue))
            throw new ValuesHaveDifferentTypesException("Expression must evaluate to a refType");
        if(!heap.contains(((RefValue)(evaluatedExpression)).getAddress()))
            throw new ElementDoesNotExistException("");
        return heap.getValue(((RefValue)(evaluatedExpression)).getAddress());
    }

    @Override
    public IType typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        if(!(expression.typeCheck(typeEnvironment) instanceof RefType))
            throw new ValueHasWrongTypeException("expression has to be a reference");
        return ((RefType) expression.typeCheck(typeEnvironment)).getInner();
    }

    public String toString(){
        return "readHeap("+expression+")";
    }
}
