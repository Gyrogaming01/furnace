package com.wildfire.furnace.compiler;

public class Variable {

    private VarType varType;
    private Object data;

    public Variable(VarType varType) {
        this.varType = varType;
    }

    public Variable(VarType varType, Object data) {
        checkVarType(data, varType);
        this.varType = varType;
        this.data = data;
    }

    public Object get() {
        return this.data;
    }

    public void set(Object data) {
        checkVarType(data, this.varType);
        this.data = data;
    }

    private void checkVarType(Object data, VarType varType) {
        if(varType.equals(VarType.BYTE) & !data.getClass().equals(Byte.class)) {
            throw new RuntimeException("Invalid variable!");
        }
        if(varType.equals(VarType.SHORT) & !data.getClass().equals(Short.class)) {
            throw new RuntimeException("Invalid variable!");
        }
        if(varType.equals(VarType.INTEGER) & !data.getClass().equals(Integer.class)) {
            throw new RuntimeException("Invalid variable!");
        }
        if(varType.equals(VarType.LONG) & !data.getClass().equals(Long.class)) {
            throw new RuntimeException("Invalid variable!");
        }
        if(varType.equals(VarType.FLOAT) & !data.getClass().equals(Float.class)) {
            throw new RuntimeException("Invalid variable!");
        }
        if(varType.equals(VarType.DOUBLE) & !data.getClass().equals(Double.class)) {
            throw new RuntimeException("Invalid variable!");
        }
    }

}
