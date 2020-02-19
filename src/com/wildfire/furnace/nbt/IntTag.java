package com.wildfire.furnace.nbt;

public class IntTag extends NbtTag {

    private int value;

    public IntTag(int value) {
        this.value = value;
    }

    public void setValue() {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

}
