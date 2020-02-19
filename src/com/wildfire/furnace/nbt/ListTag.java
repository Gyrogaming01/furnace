package com.wildfire.furnace.nbt;

import java.util.ArrayList;
import java.util.List;

public class ListTag<T extends NbtTag> extends NbtTag {

    private List<T> entries;

    public ListTag() {
        this.entries = new ArrayList<>();
    }

    public void add(T tag) {
        this.entries.add(tag);
    }

    public void remove(int index) {
        this.entries.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder entriesStr = new StringBuilder();
        for (T tag :
                this.entries) {
            entriesStr.append(tag.toString());
            entriesStr.append(",");
        }
        return String.format("[%s]", entriesStr.toString().substring(0, entriesStr.length()-1));
    }
}
