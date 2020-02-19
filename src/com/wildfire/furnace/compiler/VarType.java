package com.wildfire.furnace.compiler;

public enum VarType {
    BYTE,
    SHORT,
    INTEGER,
    LONG,
    FLOAT,
    DOUBLE,
    TAG;

    public static VarType fromString(String string) {
        if(string.equals("byte")) {
            return VarType.BYTE;
        }
        if(string.equals("short")) {
            return VarType.SHORT;
        }
        if(string.equals("int")) {
            return VarType.INTEGER;
        }
        if(string.equals("long")) {
            return VarType.LONG;
        }
        if(string.equals("float")) {
            return VarType.FLOAT;
        }
        if(string.equals("double")) {
            return VarType.DOUBLE;
        }
        if(string.equals("tag")) {
            return VarType.TAG;
        }
        throw new RuntimeException(String.format("Cannot find type '%s'", string));
    }
}
