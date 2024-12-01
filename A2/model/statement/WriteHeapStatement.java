package model.statement;

import exception.MyException;
import model.expression.IExpression;
import model.modelExceptions.ElementDoesNotExistException;
import model.modelExceptions.ValuesHaveDifferentTypesException;
import model.programState.ProgramState;
import model.type.IType;
import model.value.IValue;
import model.value.RefValue;

public class WriteHeapStatement implements IStatement{
    String variableName;
    IExpression expression;
    public WriteHeapStatement(String variableName, IExpression expression){
        this.variableName = variableName;
        this.expression = expression;
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        // check if it is in the symbol table
        if(!state.getSymbolTable().contains(variableName))
            throw new ElementDoesNotExistException("The element is not declared");
        // check if its type is refValue
        IValue v = state.getSymbolTable().getValue(variableName);
        // check if it is in the hashTable
        int heapIndex = ((RefValue)(v)).getAddress();
        if(!state.getHeap().contains(heapIndex))
            throw new ElementDoesNotExistException("wh exception 2");
        // evaluate the expression and check if the type is the same as the location type
        IValue evaluatedExpression = expression.evaluate(state.getSymbolTable(), state.getHeap());
        IType t = state.getHeap().getValue(heapIndex).getType();
        if(!evaluatedExpression.getType().equals(state.getHeap().getValue(heapIndex).getType()))
            throw new ValuesHaveDifferentTypesException("wh exception 3");
        state.getHeap().updateValue(heapIndex, evaluatedExpression);
        return  null;
    }

    public String toString(){
        return "writeHeap("+ variableName+","+expression.toString()+")";
    }
}
