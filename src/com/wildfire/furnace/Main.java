package com.wildfire.furnace;

import com.wildfire.furnace.nbt.CompoundTag;
import com.wildfire.furnace.nbt.IntTag;
import com.wildfire.furnace.nbt.ListTag;
import com.wildfire.furnace.nbt.NbtTag;

public class Main {

    public static void main(String[] args) {
        CompoundTag inner = new CompoundTag();
        inner.add("byte", (byte) 1);
        inner.add("short", (short) 2);
        inner.add("int", 3);
        inner.add("long", 4L);
        inner.add("float", 3.14f);
        inner.add("double", 6.28);
        inner.add("string", "Hello \"world");

        ListTag<IntTag> list = new ListTag<>();
        list.add(new IntTag(1));
        list.add(new IntTag(2));
        list.add(new IntTag(3));

        CompoundTag nbt = new CompoundTag();
        nbt.add("nbt", inner);
        nbt.add("byte", (byte) 1);
        nbt.add("short", (short) 2);
        nbt.add("int", 3);
        nbt.add("long", 4L);
        nbt.add("float", 3.14f);
        nbt.add("double", 6.28);
        nbt.add("string", "Hello \"world");
        nbt.add("list", list);
        System.out.println(nbt);
    }

}
