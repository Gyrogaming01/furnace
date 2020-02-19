package com.wildfire.furnace.nbt;

public class ShortTag extends NbtTag {

    private short value;

    public ShortTag(short value) {
        this.value = value;
    }

    public void setValue() {
        this.value = value;
    }

    public short getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Short.toString(this.value);
    }

}
