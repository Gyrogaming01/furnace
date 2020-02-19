package com.wildfire.furnace.compiler;

public class Compiler {

    private String namespace;

    public Compiler(String namespace) {
        setNamespace(namespace);
    }

    public String getNamespace() {
        return this.namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void compile(String filename) {

    }

    public static void main(String[] args) {
        String in = "function test(int input) {\n" +
                //"\tdo_something();\n" +
                "\ttag nbt = {\n" +
                "\t\tnum: 5,\n" +
                "\t\tstr: \"awdaw\",\n" +
                "\t\tarr: ['test', 'arr,',]\n" +
                "\t\tflt: 3.14\n" +
                "\t}\n" +
                "\tif(input==5) {\n" +
                "\t\tint x = 3;\n" +
                "\t\tdo_something_else(3); // this is a really cool thing\n" +
                "\t\ttell(@a, \"cool thing\");\n" +
                "\t\treturn true;\n" +
                "\t}\n" +
                "\treturn false;\n" +
                "}";
        LanguageParser l = new LanguageParser(in, "test");
        System.out.println(l.compile());
    }

}
