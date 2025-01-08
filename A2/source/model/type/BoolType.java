package source.model.type;

import source.model.value.BoolValue;
import source.model.value.IValue;

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
