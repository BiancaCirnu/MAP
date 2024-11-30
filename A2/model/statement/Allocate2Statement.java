package model.statement;

import exception.MyException;
import model.adt.IMyDictionary;

import model.expression.IExpression;
import model.modelExceptions.ElementDoesNotExistException;
import model.modelExceptions.ValueHasWrongTypeException;
import model.modelExceptions.ValuesHaveDifferentTypesException;
import model.programState.ProgramState;
import model.type.IType;
import model.type.RefType;
import model.value.IValue;
import model.value.IntValue;
import model.value.RefValue;
// parameters: variable and expression
// check if variable exists in the symbol table
// check if the type of the variable is "RefType"

public class Allocate2Statement implements IStatement {
    String variableName;
    IExpression expression;
    public Allocate2Statement(String variable, IExpression expression){
        this.expression = expression;
        this.variableName = variable;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IMyDictionary<String, IValue> symbolTable = state.getSymbolTable();
        if (!symbolTable.contains(variableName))
            throw new ElementDoesNotExistException("Variable is not declared");
        if (!(symbolTable.getValue(variableName).getType() instanceof RefType))
            throw new ValueHasWrongTypeException("Variable is not a ref type");
        IValue expressionValue = expression.evaluate(symbolTable, state.getHeap());
        IType locationType = ((RefValue) (symbolTable.getValue(variableName))).getLocationType();
        if (!expressionValue.getType().equals(locationType))
            throw new ValuesHaveDifferentTypesException("Referenced variable has different type than expression");
        Integer index = state.getHeap().allocate(new RefValue(state.getHeap().getCurrentKey(), locationType, expressionValue));
        state.getSymbolTable().insert(variableName, new IntValue(index));
        return null;
    }
}
