package source.model.statement;

import source.exception.MyException;
import source.model.adt.IMyDictionary;
import source.model.expression.IExpression;
import source.model.modelExceptions.OpenFileException;
import source.model.modelExceptions.ValueHasWrongTypeException;
import source.model.programState.ProgramState;
import source.model.type.IType;
import source.model.type.StringType;
import source.model.value.IValue;
import source.model.value.StringValue;

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
    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        if (!expression.typeCheck(typeEnvironment).equals(new StringType()))
            throw new ValueHasWrongTypeException("expression must return a string");
        return typeEnvironment;
    }

    public String toString(){
        return "fopen("+expression.toString()+")";
    }

}
