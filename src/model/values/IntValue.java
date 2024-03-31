package model.values;

import model.types.Type;
import model.types.IntType;

public class IntValue implements Value {
    private final int value;

    public IntValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof IntValue && this.value == ((IntValue) another).getValue();
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    @Override
    public Value deepCopy() {
        return new IntValue(this.value);
    }

    public String toString() {
        return Integer.toString(this.value);
    }
}
