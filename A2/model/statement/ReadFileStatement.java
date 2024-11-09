package model.statement;

import exception.MyException;
import model.expression.IExpression;
import model.programState.ProgramState;
import model.type.IntType;
import model.type.StringType;
import model.value.IValue;
import model.value.IntValue;
import model.value.StringValue;

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
        var t = state.getSymbolTable();
        if (!t.contains(varName)) {
            throw new MyException("Variable '" + varName + "' does not exists");
        }
        if(!t.getValue(varName).getType().equals(new IntType())) {
            throw new MyException("Variable '" + varName + "' does not match expression '" + expression + "'");
        }
        IValue result = expression.evaluate(state.getSymbolTable());

        if (!result.getType().equals(new StringType())) {
            throw new MyException("Variable '" + varName + "' is not a string");
        }

        BufferedReader r = state.getFileTable().getValue(((StringValue) result).getValue());
        try{
            String readResult = r.readLine();
            if (readResult.isEmpty()) {
                readResult = "0";
            }
            int parsedResult = Integer.parseInt(readResult);

            state.getSymbolTable().insert(varName, new IntValue(parsedResult));
            return state;

        } catch (IOException e) {
            throw new MyException("I/O Exception trying to read file " + ((StringValue) result).getValue());
        }
    }
}
