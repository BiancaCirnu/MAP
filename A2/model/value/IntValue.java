package model.value;

import com.sun.jdi.Value;
import model.modelExceptions.ValuesHaveDifferentTypesException;
import model.type.IType;
import model.type.IntType;

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
