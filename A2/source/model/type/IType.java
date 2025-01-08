package source.model.type;

import source.model.value.IValue;

public interface IType {
    public boolean equals(IType other);
    public IValue getDefaultValue();
}

