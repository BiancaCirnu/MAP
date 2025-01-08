package source.model.value;

import source.model.modelExceptions.ValuesHaveDifferentTypesException;
import source.model.type.IType;
import source.model.type.RefType;

public class RefValue implements IValue{
    int address;
    IType locationType;
    IValue value;
    public RefValue(int addr, IType locType,IValue value ) {
        this.address = addr;
        this.locationType = locType;
        this.value = value;
    }
    public IType getType(){
        return new RefType(locationType);
    }
    public IType getLocationType(){
        return locationType;
    }
    public boolean equals(IValue value) throws ValuesHaveDifferentTypesException{
        if(!value.getType().equals(new RefType(this.locationType)))
            throw new ValuesHaveDifferentTypesException("Value has the wrong type.");
        return this.locationType.equals(((RefValue)value).locationType) && this.address == ((RefValue)value).address;
    }
    public int getAddress(){
        return address;
    }
    public String toString()
    {
        return "->"+address;
    }
}
