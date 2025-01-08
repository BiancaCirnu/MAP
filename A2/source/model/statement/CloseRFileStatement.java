package source.model.statement;

import source.exception.MyException;
import source.model.adt.IMyDictionary;
import source.model.expression.IExpression;
import source.model.modelExceptions.ElementDoesNotExistException;
import source.model.modelExceptions.ValueHasWrongTypeException;
import source.model.programState.ProgramState;
import source.model.type.IType;
import source.model.type.StringType;
import source.model.value.IValue;
import source.model.value.StringValue;

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
