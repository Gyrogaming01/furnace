package com.wildfire.furnace.compiler;

import java.util.ArrayList;
import java.util.List;

public class Function {

    private String namespace;
    private String funcName;
    private List<Command> commands;

    public Function(String namespace, String funcName) {
        this.namespace = namespace;
        this.funcName = funcName;
        this.commands = new ArrayList<>();
    }

    public Function(String namespace, String funcName, List<Command> commands) {
        this.namespace = namespace;
        this.funcName = funcName;
        this.commands = commands;
    }

    public void write() {

    }
}
