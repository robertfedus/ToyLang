package model.values;

import model.types.BoolType;
import model.types.IntType;
import model.types.Type;

public class BoolValue implements Value {
    private final boolean value;

    public BoolValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof BoolValue && this.value == ((BoolValue) another).getValue();
    }

    public boolean getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new BoolType();
    }

    public String toString() {
        return Boolean.toString(this.value);
    }
}
