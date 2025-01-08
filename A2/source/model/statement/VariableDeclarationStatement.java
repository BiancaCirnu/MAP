package source.model.statement;

import source.exception.MyException;
import source.model.adt.IMyDictionary;
import source.model.modelExceptions.ElementExistsException;
import source.model.programState.ProgramState;
import source.model.type.IType;

public class VariableDeclarationStatement implements IStatement{
    private String variableName;
    private IType variableType;

    public VariableDeclarationStatement(String variableName, IType variableType) {
        this.variableName = variableName;
        this.variableType = variableType;
    }

    public ProgramState execute(ProgramState state) throws MyException {
        if (state.getSymbolTable().contains(variableName))
            throw new ElementExistsException();
        else
            state.getSymbolTable().insert(variableName, variableType.getDefaultValue());
        return null;
    }

    public String toString() {
        return variableType.toString() + " " + variableName;
    }

    @Override
    public IMyDictionary<String, IType> typeCheck(IMyDictionary<String, IType> typeEnvironment) throws MyException {
        typeEnvironment.insert(variableName, variableType);
        return typeEnvironment;
    }
}

