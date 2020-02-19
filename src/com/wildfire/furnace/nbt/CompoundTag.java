package com.wildfire.furnace.nbt;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CompoundTag extends NbtTag {

    private Map<String, NbtTag> entries;

    public CompoundTag() {
        this.entries = new HashMap<>();
    }

    public static CompoundTag fromTokens(String[] tokens, Map<String, Object> scopeObjs) {
        return (CompoundTag) TagParser.parse(tokens, scopeObjs);
    }

    public void add(String name, byte tag) {
        this.entries.put(name, new ByteTag(tag));
    }

    public void add(String name, short tag) {
        this.entries.put(name, new ShortTag(tag));
    }

    public void add(String name, int tag) {
        this.entries.put(name, new IntTag(tag));
    }

    public void add(String name, long tag) {
        this.entries.put(name, new LongTag(tag));
    }

    public void add(String name, float tag) {
        this.entries.put(name, new FloatTag(tag));
    }

    public void add(String name, double tag) {
        this.entries.put(name, new DoubleTag(tag));
    }

    public void add(String name, String tag) {
        this.entries.put(name, new StringTag(tag));
    }

    public void add(String name, NbtTag tag) {
        this.entries.put(name, tag);
    }

    public void remove(String name) {
        this.entries.remove(name);
    }

    public NbtTag get(String name) {
        return this.entries.get(name);
    }

    @Override
    public String toString() {
        StringBuilder entriesStr = new StringBuilder();
        for (String key :
                this.entries.keySet()) {
            entriesStr.append(key);
            entriesStr.append(":");
            entriesStr.append(this.entries.get(key).toString());
            entriesStr.append(",");
        }
        return String.format("{%s}", entriesStr.toString().substring(0, entriesStr.length()-1));
    }
}
