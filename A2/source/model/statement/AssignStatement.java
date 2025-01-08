package source.model.statement;

import source.model.adt.IMyDictionary;
import source.model.expression.IExpression;
import source.model.modelExceptions.ElementDoesNotExistException;
import source.exception.MyException;
import source.model.modelExceptions.ValueHasWrongTypeException;
import source.model.programState.ProgramState;
import source.model.type.IType;
import source.model.value.IValue;

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
