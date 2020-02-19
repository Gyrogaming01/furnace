package com.wildfire.furnace.utils;

public class Regex {

    public static String RESERVED_REGEX =
            "import|class|function|byte|short|int|long|float|double|string|tag|if|for|while|return";
    public static String VARNAME_REGEX = "[A-Za-z_][A-Za-z0-9_]*";
    public static String VARTYPE_REGEX = "byte|short|int|long|float|double|string|tag";

    // DATATYPE REGEX
    public static String STRING_REGEX = "\"(\\\\[\"\\\\ntr]|[^\"\\\\])*\"" + "|'(\\\\['\\\\ntr]|[^'\\\\])*'";
    public static String DOUBLE_REGEX = "[0-9]+\\.[0-9]+";
    public static String FLOAT_REGEX = "[0-9]+\\.[0-9]+f";
    public static String BYTE_REGEX = "-?0x[0-9A-F]+b" + "|-?0b[0-1]+b" + "|-?0[0-9]+b" + "|-?[0-9]+b";
    public static String SHORT_REGEX = "-?0x[0-9A-F]+s" + "|-?0b[0-1]+s" + "|-?0[0-9]+s" + "|-?[0-9]+s";
    public static String INT_REGEX = "-?0x[0-9A-F]+" + "|-?0b[0-1]+" + "|-?0[0-9]+" + "|-?[0-9]+";
    public static String LONG_REGEX = "-?0x[0-9A-F]+L" + "|-?0b[0-1]+L" + "|-?0[0-9]+L" + "|-?[0-9]+L";


}
