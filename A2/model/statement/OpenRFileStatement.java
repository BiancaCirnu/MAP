package model.statement;

import exception.MyException;
import model.adt.IMyStack;
import model.expression.IExpression;
import model.modelExceptions.OpenFileException;
import model.modelExceptions.ValueHasWrongTypeException;
import model.programState.ProgramState;
import model.type.StringType;
import model.value.IValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFileStatement implements IStatement{
    IExpression expression;
    public OpenRFileStatement(IExpression expression) {
        this.expression = expression;
    }
    public ProgramState execute(ProgramState state) throws MyException {
        IValue fileNameValue = expression.evaluate(state.getSymbolTable());
        if(!fileNameValue.getType().equals(new StringType()))
            throw new ValueHasWrongTypeException("Value must be a string");
        if(state.getFileTable().contains(((StringValue)fileNameValue).getValue()))
            throw new MyException("File is already opened");
        try{
            BufferedReader opened = new BufferedReader(new FileReader(((StringValue)fileNameValue).getValue()));
            state.getFileTable().insert(((StringValue)fileNameValue).getValue(), opened);
        }
        catch (IOException e)
        {
            throw new OpenFileException("Failed to open the file");
        }
        return state;

    }

    public String toString(){
        return "fopen("+expression.toString()+")";
    }

}
