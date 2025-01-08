package source.model.type;

import source.model.value.IValue;
import source.model.value.StringValue;

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

