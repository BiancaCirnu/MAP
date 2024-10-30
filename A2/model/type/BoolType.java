package model.type;

import model.value.BoolValue;
import model.value.IValue;

public class BoolType implements IType {
    @Override
    public boolean equals(IType other){
        return other instanceof BoolType;
    }
    @Override
    public IValue getDefaultValue(){
        return new BoolValue();
    }
    public String toString(){
        return "Bool";
    }
}
