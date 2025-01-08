package source.model.value;

import source.model.modelExceptions.ValuesHaveDifferentTypesException;
import source.model.type.IType;

public interface IValue {
    public IType getType();
    public boolean equals(IValue value) throws ValuesHaveDifferentTypesException;
}
