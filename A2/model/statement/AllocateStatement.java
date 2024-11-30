package model.statement;

import exception.MyException;
import model.expression.IExpression;
import model.modelExceptions.ElementDoesNotExistException;
import model.modelExceptions.ValueHasWrongTypeException;
import model.modelExceptions.ValuesHaveDifferentTypesException;
import model.programState.ProgramState;
import model.type.RefType;
import model.value.IValue;
import model.value.IntValue;
import model.value.RefValue;

public class AllocateStatement implements IStatement{
    private final String variableName;
    private final IExpression expression;
    public AllocateStatement(String variableName, IExpression expression){
        this.expression = expression;
        this.variableName = variableName;
    }
    public ProgramState execute(ProgramState state) throws MyException{
        if (!state.getSymbolTable().contains(variableName)) {
            throw new ElementDoesNotExistException("There is no variable with the name: " + variableName);
        }

        IValue variableValue = state.getSymbolTable().getValue(variableName);
        if (!(variableValue.getType() instanceof RefType)) {
            throw new ValueHasWrongTypeException("Variable '" + variableName + "' is not a reference type");
        }

        RefValue variableToAllocate = (RefValue) variableValue;
        RefType variableType = (RefType) variableToAllocate.getType();

        IValue expressionValue = expression.evaluate(state.getSymbolTable(), state.getHeap());

        if (!expressionValue.getType().equals(variableType.getInner())) {
            throw new ValuesHaveDifferentTypesException("Expression type does not match the expected reference type");
        }

        int addressInHeap = state.getHeap().allocate(expressionValue);
        state.getSymbolTable().insert(variableName, new IntValue(addressInHeap));
        return state;
    }


    public String toString(){
        return "NewRef("+ variableName+", " + expression +")";
    }
}
