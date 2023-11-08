package model.values;

import model.types.Type;
import model.types.IntType;

public class IntValue implements Value {
    private final int value;

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new IntType();
    }

    public String toString() {
        return Integer.toString(this.value);
    }
}
