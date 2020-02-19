package com.wildfire.furnace.nbt;

public class StringTag extends NbtTag {

    private String value;

    public StringTag(String value) {
        this.value = value;
    }

    public void setValue() {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "\"" + this.value.replaceAll("\"", "\\\\\"") + "\"";
    }

}
