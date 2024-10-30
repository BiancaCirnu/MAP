package model.value;

import model.modelExceptions.ValuesHaveDifferentTypesException;
import model.type.IType;

public interface IValue {
    public IType getType();
    public boolean equals(IValue value) throws ValuesHaveDifferentTypesException;
}
