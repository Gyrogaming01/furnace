package com.wildfire.furnace.nbt;

public class FloatTag extends NbtTag {

    private float value;

    public FloatTag(float value) {
        this.value = value;
    }

    public void setValue() {
        this.value = value;
    }

    public float getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Float.toString(this.value);
    }

}
