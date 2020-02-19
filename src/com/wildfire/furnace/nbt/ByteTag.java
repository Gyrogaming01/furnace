package com.wildfire.furnace.nbt;

public class ByteTag extends NbtTag {

    private byte value;

    public ByteTag(byte value) {
        this.value = value;
    }

    public void setValue() {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Byte.toString(this.value);
    }

}
