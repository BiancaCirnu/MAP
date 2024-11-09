package model.value;

import com.sun.jdi.Value;
import model.modelExceptions.ValuesHaveDifferentTypesException;
import model.type.IType;
import model.type.StringType;

public class StringValue implements IValue {
    private String value;
    public StringValue(String value) {
        this.value = value;
    }
    public StringValue(){
        this.value = "";
    }
    public String getValue() {
        return value;
    }
    public IType getType() { return new StringType(); }
    public boolean equals(IValue other) throws ValuesHaveDifferentTypesException {
        if(!getType().equals(other.getType()))
            throw new ValuesHaveDifferentTypesException();
        else
            return ((StringValue)other).getValue().equals(value);
    }
    public String toString(){
        return value;
    }
}
