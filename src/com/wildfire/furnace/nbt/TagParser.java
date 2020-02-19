package com.wildfire.furnace.nbt;

import com.wildfire.furnace.utils.Regex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TagParser {

    public static NbtTag parse(String[] tokens, Map<String, Object> scopeObjs) {
        CompoundTag nbt = new CompoundTag();
        for (int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals("{")) {
                continue;
            } else if(tokens[i].matches(Regex.VARNAME_REGEX)) {
                if(tokens[i+1].equals(":")) {
                    if(tokens[i+2].equals("{")) {
                        // parse inner tag
                    } else if (tokens[i+2].equals("[")) {
                        // parse inner list
                    } else if (tokens[i+2].matches(Regex.STRING_REGEX)) {
                        nbt.add(tokens[i], new StringTag(tokens[i+2]));
                        i+=2;
                    } else if (tokens[i+2].matches(Regex.DOUBLE_REGEX)) {
                        nbt.add(tokens[i], new DoubleTag(Double.parseDouble(tokens[i+2])));
                        i+=2;
                    } else if (tokens[i+2].matches(Regex.FLOAT_REGEX)) {
                        float num = Float.parseFloat(tokens[i+2].substring(0, tokens[i+3].length() - 1));
                        nbt.add(tokens[i], new FloatTag(num));
                        i+=2;
                    } else if (tokens[i+2].matches(Regex.BYTE_REGEX)) {
                        byte num = Byte.parseByte(tokens[i+2].substring(0, tokens[i+3].length() - 1));
                        nbt.add(tokens[i], new ByteTag(num));
                        i+=2;
                    } else if (tokens[i+2].matches(Regex.SHORT_REGEX)) {
                        short num = Short.parseShort(tokens[i+2].substring(0, tokens[i+3].length() - 1));
                        nbt.add(tokens[i], new ShortTag(num));
                        i+=2;
                    } else if (tokens[i+2].matches(Regex.INT_REGEX)) {
                        int num = Integer.parseInt(tokens[i+2]);
                        nbt.add(tokens[i], new IntTag(num));
                        i+=2;
                    } else if (tokens[i+2].matches(Regex.LONG_REGEX)) {
                        long num = Long.parseLong(tokens[i+2].substring(0, tokens[i+3].length() - 1));
                        nbt.add(tokens[i], new LongTag(num));
                        i+=2;
                    } else if (tokens[i+2].matches(Regex.VARNAME_REGEX)) {
                        int j = i+3;
                        int used = 2;
                        List<String> vars = new ArrayList<>();
                        vars.add(tokens[i+2]);
                        while (tokens[j].equals(".")) {
                            if(!tokens[j+1].matches(Regex.VARNAME_REGEX)) {
                                throw new RuntimeException("Cannot parse variable!");
                            }
                            vars.add(tokens[j+1]);
                            j+=2;
                            used+=2;
                        }
                        Object obj = null;
                        for (String var :
                                vars) {
                            obj = scopeObjs.get(var);
                        }
                        nbt.add(tokens[i], (NbtTag) obj);
                        i+=used;
                    } else {
                        throw new RuntimeException("Cannot parse NBT tag!");
                    }
                }
            }
        }
        return nbt;
    }

}
