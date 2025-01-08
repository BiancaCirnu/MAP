package source.model.value;

import source.model.modelExceptions.ValuesHaveDifferentTypesException;
import source.model.type.BoolType;
import source.model.type.IType;

public class BoolValue implements IValue {
    private boolean value;
    public BoolValue(boolean value) {
        this.value = value;
    }
    public BoolValue() {
        this.value = false;
    }
    public boolean getValue() {
        return value;
    }
    public IType getType() {
        return new BoolType();
    }
    public boolean equals(IValue other) throws ValuesHaveDifferentTypesException {
        if (!getType().equals(other.getType()))
            throw new ValuesHaveDifferentTypesException();
        else {
            return ((BoolValue)other).getValue() == value;
        }
    }
    public String toString() {
        return "" + value;
    }
}
