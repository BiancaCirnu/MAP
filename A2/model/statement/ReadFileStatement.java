package model.statement;

import exception.MyException;
import model.adt.IMyDictionary;
import model.expression.IExpression;
import model.expression.ValueExpression;
import model.modelExceptions.FileIsNotOpenException;
import model.modelExceptions.OpenFileException;
import model.modelExceptions.ValueHasWrongTypeException;
import model.programState.ProgramState;
import model.type.IType;
import model.type.IntType;
import model.type.StringType;
import model.value.IValue;
import model.value.IntValue;
import model.value.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement{
    private IExpression expression;
    private String varName;
    public ReadFileStatement(IExpression expression, String varName){
        this.expression = expression;
        this.varName = varName;
    }
    public ProgramState execute(ProgramState state) throws MyException {
        IValue fileNameValue = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (!(fileNameValue instanceof StringValue)) {
            throw new ValueHasWrongTypeException("Expected StringValue for file name");
        }
        if(!fileNameValue.getType().equals(new StringType()))
            throw new ValueHasWrongTypeException("Value has to be a string");
        if (!state.getFileTable().contains(((StringValue) fileNameValue).getValue()))
            throw new FileIsNotOpenException("");
        BufferedReader reader = state.getFileTable().getValue(((StringValue) fileNameValue).getValue());
        try{
            String line = reader.readLine();
            if (line  == null)
                new AssignStatement(varName, new ValueExpression(new IntValue(0))).execute(state);
            else
                new AssignStatement(varName, new ValueExpression(new IntValue(Integer.parseInt(line)))).execute(state);
        }catch (IOException e) {
            new AssignStatement(varName, new ValueExpression(new IntValue(0))).execute(state);
        }
        return null;
    }

    @Override
    public String toString() {
        return "readFile("+ expression.toString()+ ','+ varName+")";
    }

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        if (!expression.typeCheck(typeEnvironment).equals(new StringType()))
            throw new ValueHasWrongTypeException("expression must return a string");
        if (!typeEnvironment.contains(varName))
            throw new ValueHasWrongTypeException("variable is not declared");
        return typeEnvironment;
    }
}
