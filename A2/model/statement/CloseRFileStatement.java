package model.statement;

import exception.MyException;
import model.expression.IExpression;
import model.modelExceptions.ElementDoesNotExistException;
import model.modelExceptions.ValueHasWrongTypeException;
import model.programState.ProgramState;
import model.type.StringType;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements IStatement{
    IExpression expression;
    public CloseRFileStatement(IExpression expression) {
        this.expression = expression;
    }
    public ProgramState execute(ProgramState state) throws MyException {
        IValue  fileNameValue = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if(!fileNameValue.getType().equals(new StringType()))
            throw new ValueHasWrongTypeException("Value is not a string");
        StringValue filename = (StringValue) fileNameValue;
        if(!state.getFileTable().contains(((StringValue) fileNameValue).getValue()))
            throw new ElementDoesNotExistException("");
        BufferedReader fileToClose  = state.getFileTable().getValue(filename.getValue());
        try
        {
            fileToClose.close();
            state.getFileTable().remove(filename.getValue());
        } catch (IOException e) {
            throw new MyException("");
        }
        return state;
    }

    public String toString()
    {
        return "fclose("+expression.toString()+")";
    }
}
