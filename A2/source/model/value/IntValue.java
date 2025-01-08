package source.model.value;

import source.model.modelExceptions.ValuesHaveDifferentTypesException;
import source.model.type.IType;
import source.model.type.IntType;

public class IntValue implements IValue {
    private int value;
    public IntValue(int value) {
        this.value = value;
    }
    public IntValue() {
        this.value = 0;
    }
    public int getValue() {
        return value;
    }
    public IType getType() {
        return new IntType();
    }
    public boolean equals(IValue other) throws ValuesHaveDifferentTypesException {
        if (!getType().equals(other.getType()))
            throw new ValuesHaveDifferentTypesException();
        else {
            return ((IntValue)other).getValue() == value;
        }
    }

    public String toString() {
        return "" + value;
    }
}
