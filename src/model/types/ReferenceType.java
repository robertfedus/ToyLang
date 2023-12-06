package model.types;

import model.values.ReferenceValue;
import model.values.Value;

public class ReferenceType implements Type {
    Type inner;
    public ReferenceType(Type inner) {
        this.inner = inner;
    }

    public Type getInner() {
        return this.inner;
    }

    public boolean equals(Object another) {
        if (another instanceof ReferenceType) {
            return this.inner.equals(((ReferenceType) another).getInner());
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Reference(" + this.inner.toString() + ")";
    }

    public Value defaultValue() {
        return new ReferenceValue(0, this.inner);
    }
}
