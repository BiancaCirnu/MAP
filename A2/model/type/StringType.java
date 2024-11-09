package model.type;

import model.value.IValue;
import model.value.StringValue;

public class StringType implements IType {
    public boolean equals(IType other) {
        return other instanceof StringType;
    }
    public IValue getDefaultValue(){
        return new StringValue();
    }
    public String toString(){
        return "String";
    }
}

