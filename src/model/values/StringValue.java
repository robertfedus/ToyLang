package model.values;

import model.types.IntType;
import model.types.StringType;
import model.types.Type;

import java.util.Objects;

public class StringValue implements Value {
    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object another) {
        return another instanceof StringValue && Objects.equals(this.value, ((StringValue) another).getValue());
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    public String toString() {
        return this.value;
    }
}
