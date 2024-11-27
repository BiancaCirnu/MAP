package model.type;

import model.value.IValue;

public interface IType {
    public boolean equals(IType other);
    public IValue getDefaultValue();
}

