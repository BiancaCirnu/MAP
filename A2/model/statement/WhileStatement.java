package model.statement;

import exception.MyException;
import model.adt.IMyDictionary;
import model.adt.MyStack;
import model.expression.IExpression;
import model.modelExceptions.ValueHasWrongTypeException;
import model.programState.ProgramState;
import model.type.BoolType;
import model.type.IType;
import model.value.BoolValue;
import model.value.IValue;

public class WhileStatement implements IStatement{
    private final IExpression expression;
    private final IStatement body;

    public WhileStatement(IExpression expr, IStatement body) {
        this.expression = expr;
        this.body = body;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        IValue exprValue = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (!(exprValue.getType() instanceof BoolType))
            throw new MyException("While condition is not of type bool.");

        BoolValue condition = (BoolValue) exprValue;
        if (condition.getValue()) {
            state.getExecutionStack().push(this);
            state.getExecutionStack().push(body);
        }
        return null;
    }
    public String toString(){
        String s = "while(" + expression.toString()+") {"+body.toString()+"}";
        return s;
    }

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        if(!expression.typeCheck(typeEnvironment).equals(new BoolType()))
            throw new ValueHasWrongTypeException("The expression must return a bool value");
        return body.typeCheck(typeEnvironment);
    }
}
