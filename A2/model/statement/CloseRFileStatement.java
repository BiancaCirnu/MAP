package model.statement;

import exception.MyException;
import model.adt.IMyDictionary;
import model.expression.IExpression;
import model.modelExceptions.ElementDoesNotExistException;
import model.modelExceptions.ValueHasWrongTypeException;
import model.programState.ProgramState;
import model.type.IType;
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
        return null;
    }

    public String toString()
    {
        return "fclose("+expression.toString()+")";
    }

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        if(!expression.typeCheck(typeEnvironment).equals(new StringType()))
            throw new ValueHasWrongTypeException("expression must return a string");
        return typeEnvironment;
    }
}
