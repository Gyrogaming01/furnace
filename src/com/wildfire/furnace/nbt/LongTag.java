package com.wildfire.furnace.nbt;

public class LongTag extends NbtTag {

    private long value;

    public LongTag(long value) {
        this.value = value;
    }

    public void setValue() {
        this.value = value;
    }

    public long getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Long.toString(this.value);
    }

}
