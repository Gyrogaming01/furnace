package com.wildfire.furnace.nbt;

public class DoubleTag extends NbtTag {

    private double value;

    public DoubleTag(double value) {
        this.value = value;
    }

    public void setValue() {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return Double.toString(this.value);
    }

}
