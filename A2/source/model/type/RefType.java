package source.model.type;

import source.model.value.IValue;
import source.model.value.RefValue;

public class RefType implements IType{
    IType inner;
    public RefType(IType inner){
        this.inner = inner;
    }
    public IType getInner(){
        return inner;
    }
    public boolean equals(IType other){
        if(other instanceof RefType)
            return inner.equals(((RefType) other).getInner());
        else
            return false;
    }
    public IValue getDefaultValue(){
        return new RefValue(0, inner, inner.getDefaultValue());
    }
    public String toString(){
        return "Ref("+ inner.toString()+")";
    }
}
