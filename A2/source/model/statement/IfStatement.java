package source.model.statement;

import source.exception.MyException;
import source.model.adt.IMyDictionary;
import source.model.expression.IExpression;
import source.model.modelExceptions.ValueHasWrongTypeException;
import source.model.programState.ProgramState;
import source.model.type.BoolType;
import source.model.type.IType;
import source.model.value.BoolValue;

public class IfStatement implements IStatement {
    IExpression condition;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(IExpression condition, IStatement thenStatement, IStatement elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    public ProgramState execute(ProgramState state) throws MyException {
        if(!(condition.evaluate(state.getSymbolTable(), state.getHeap()).getType()).equals(new BoolType()))
            throw new ValueHasWrongTypeException();
        BoolValue res = (BoolValue) condition.evaluate(state.getSymbolTable(), state.getHeap());
        if (res.equals(new BoolValue(true)))
            state.getExecutionStack().push(thenStatement);
        else
            state.getExecutionStack().push(elseStatement);
        return null;
    }
    public String toString() {
        return "if " + condition.toString() + " then " + thenStatement.toString() + " else " + elseStatement.toString();
    }

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        if(!condition.typeCheck(typeEnvironment).equals(new BoolType()))
            throw new ValueHasWrongTypeException("the condition must return a bool value");
        thenStatement.typeCheck(typeEnvironment.deepCopy());
        elseStatement.typeCheck(typeEnvironment.deepCopy());
        return typeEnvironment;
    }
}
