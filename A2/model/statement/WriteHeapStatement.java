package model.statement;

import exception.MyException;
import model.expression.IExpression;
import model.modelExceptions.ElementDoesNotExistException;
import model.modelExceptions.ValuesHaveDifferentTypesException;
import model.programState.ProgramState;
import model.type.IType;
import model.type.RefType;
import model.value.IValue;
import model.value.IntValue;
import model.value.RefValue;

public class WriteHeapStatement implements IStatement{
    private final String variableName;
    private final IExpression expression;
    public WriteHeapStatement(String varName, IExpression expr){
        variableName = varName;
        expression = expr;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        if (!state.getSymbolTable().contains(variableName)) {
            throw new ElementDoesNotExistException("Variable '" + variableName + "' does not exist in the symbol table.");
        }

        IValue variableValue = state.getSymbolTable().getValue(variableName);
        if (!(variableValue instanceof RefValue)) {
            throw new ValuesHaveDifferentTypesException("Variable '" + variableName + "' is not a reference type.");
        }

        RefValue refValue = (RefValue) variableValue;
        if (!state.getHeap().contains(refValue.getAddress())) {
            throw new MyException("Heap does not contain the specified address: " + refValue.getAddress());
        }

        IValue exprValue = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (!exprValue.getType().equals(refValue.getLocationType())) {
            throw new ValuesHaveDifferentTypesException("Type of the evaluated expression does not match the referenced type.");
        }

        state.getHeap().updateValue(refValue.getAddress(), exprValue);
        state.getSymbolTable().insert(variableName, new IntValue(refValue.getAddress()));
        return null;
    }
    public String toString(){
        return "WriteHeap("+variableName+","+expression.toString()+")";
    }
}
