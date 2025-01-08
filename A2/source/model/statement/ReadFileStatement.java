package source.model.statement;

import source.exception.MyException;
import source.model.adt.IMyDictionary;
import source.model.expression.IExpression;
import source.model.expression.ValueExpression;
import source.model.modelExceptions.FileIsNotOpenException;
import source.model.modelExceptions.ValueHasWrongTypeException;
import source.model.programState.ProgramState;
import source.model.type.IType;
import source.model.type.StringType;
import source.model.value.IValue;
import source.model.value.IntValue;
import source.model.value.StringValue;

import java.io.BufferedReader;
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
