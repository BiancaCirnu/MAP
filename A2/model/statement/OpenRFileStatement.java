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
        IValue fileNameValue = expression.evaluate(state.getSymbolTable(), state.getHeap());

        if (!(fileNameValue instanceof StringValue))
            throw new ValueHasWrongTypeException("Expected StringValue for file name");

        StringValue fileNameString = (StringValue) fileNameValue;

        if (state.getFileTable().contains(fileNameString.getValue()))
            throw new MyException("File is already opened");

        try {
            BufferedReader opened = new BufferedReader(new FileReader(fileNameString.getValue()));
            state.getFileTable().insert(fileNameString.getValue(), opened);
        } catch (IOException e) {
            throw new OpenFileException("Failed to open the file: " + fileNameString.getValue());
        }

        return null;
    }


    public String toString(){
        return "fopen("+expression.toString()+")";
    }

}
