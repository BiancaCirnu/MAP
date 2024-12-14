package model.statement;

import model.adt.IMyDictionary;
import model.expression.IExpression;
import model.modelExceptions.ElementDoesNotExistException;
import exception.MyException;
import model.modelExceptions.ValueHasWrongTypeException;
import model.programState.ProgramState;
import model.type.IType;
import model.value.IValue;

public class AssignStatement implements IStatement {
    private String variable;
    private IExpression expression;
    public AssignStatement(String variable, IExpression expression) {
        this.variable = variable;
        this.expression = expression;
    }
    public ProgramState execute(ProgramState state) throws MyException {
        if(!state.getSymbolTable().contains(variable)) {
            throw new ElementDoesNotExistException();
        }
        IType varType = state.getSymbolTable().getValue(variable).getType();
        IValue exprValue = expression.evaluate(state.getSymbolTable(), state.getHeap());
        if (!varType.equals(exprValue.getType()))
            throw new MyException("Variable '" + variable + "' does not match expression '" + expression + "'");
        else
            state.getSymbolTable().insert(variable, exprValue);
        return null;
    }
    public String toString() {
        return variable + " = " + expression;
    }

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        IType typeVar = typeEnvironment.getValue(variable);
        IType typeExpression = expression.typeCheck(typeEnvironment);
        if(typeExpression.equals(typeVar))
            return typeEnvironment;
        throw new ValueHasWrongTypeException("Variable and expression must have the same type");
    }
}
