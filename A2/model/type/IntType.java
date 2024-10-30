package model.type;

import model.value.IntValue;
import model.value.IValue;

public class IntType implements IType {
    @Override
    public boolean equals(IType other){
        return other instanceof IntType;
    }
    @Override
    public IValue getDefaultValue(){
        return new IntValue();
    }
    public String toString(){
        return "Int";
    }
}
