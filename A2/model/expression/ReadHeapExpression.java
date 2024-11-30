package model.expression;

import exception.MyException;
import model.adt.IMyDictionary;
import model.adt.IMyHeap;
import model.adt.MyHeap;
import model.modelExceptions.ElementDoesNotExistException;
import model.modelExceptions.ValueHasWrongTypeException;
import model.type.*;
import model.value.IValue;
import model.value.RefValue;

public class ReadHeapExpression implements IExpression{

    private IExpression expression;
    public ReadHeapExpression(IExpression e){
        expression = e;
    }
    
    @Override
    public IValue evaluate(IMyDictionary<String, IValue> symbolTable, IMyHeap heap) throws MyException {
        IValue expressionValue = expression.evaluate(symbolTable, heap);
        if(!(expressionValue instanceof RefValue refValue))
            throw new ValueHasWrongTypeException("");
        int address = refValue.getAddress();
        IValue value = heap.getValue(address);
        if(value == null)
            throw new MyException("Address is not allocated in heap");
        return value;
    }

    @Override
    public IType typeCheck(IMyDictionary<String, IType> typeTable) throws MyException {
        IType expressionType = expression.typeCheck(typeTable);
        if (!(expressionType instanceof RefType))
            throw new MyException("Read Heap expression is not of type reference!");
        return ((RefType) expressionType).getInner();
    }

    public String toString(){
        return "ReadHeap(" + expression.toString()+")";
    }
}
