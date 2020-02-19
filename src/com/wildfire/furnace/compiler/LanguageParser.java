package com.wildfire.furnace.compiler;

import com.wildfire.furnace.nbt.CompoundTag;
import com.wildfire.furnace.utils.Regex;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageParser {

    private static String TOKEN_REGEX =
            "\"(\\\\[\"\\\\ntr]|[^\"\\\\])*\"" +        // Double-quote String                      "test"
                    "|'(\\\\['\\\\ntr]|[^'\\\\])*'" +   // Single-quote String                      'test'
                    "|[0-9]+\\.[0-9]+" +                // Double                                    3.14
                    "|0x[0-9A-F]+" +                    // Hexadecimal number                       0xFF
                    "|0b[0-1]+" +                       // Binary number                            0b10101
                    "|0[0-9]+" +                        // Octal number                             0744
                    "|[0-9]+" +                         // Decimal number                           10000
                    "|#[^\\r\\n]*"+                     // Comment                                  # This is a comment
                    "|//[^;\\r\\n]*" +                  // Minecraft command (extra slash needed)   //weather clear
                    "|[A-Za-z_][A-Za-z0-9_]*" +         // Token                                    varname
                    "|@[aeprs]" +                       // Minecraft Entity Reference               @a
                    "|===" +                            // Strong comparison                        ===
                    "|==|<=|>=|<|>|!=" +                // Comparison                               ==, >, etc.
                    "|[(){}\\[\\].+\\-*/%\"'!;=,:]";    // Symbols                                  {, !, etc.

    private String namespace;
    private String data;
    private List<Function> generatedFiles;

    public LanguageParser(String data, String namespace) {
        this.namespace = namespace;
        this.data = data;
        this.generatedFiles = new ArrayList<>();
    }

    public List<String> lex() {
        Pattern tokenPattern = Pattern.compile(TOKEN_REGEX);
        List<String> tokens = new ArrayList<>();
        Matcher tokenMatcher = tokenPattern.matcher(this.data);
        while(tokenMatcher.find()) {
            tokens.add(tokenMatcher.group());
        }
        return tokens;
    }

    private int findEndOfScope(String[] tokens) {
        int scope = 0;
        for (int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals("{")) {
                scope++;
            } else if(tokens[i].equals("}")) {
                if(scope == 0) {
                    return i;
                } else {
                    scope --;
                }
            }
        }
        return -1;
    }

    private int findEndOfExpression(String[] tokens) {
        int scope = 0;
        for (int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals("(")) {
                scope++;
            } else if(tokens[i].equals(")")) {
                if(scope == 0) {
                    return i;
                } else {
                    scope --;
                }
            }
        }
        return -1;
    }

    private int findEndOfStatement(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            if(tokens[i].equals(";")) {
                return i;
            }
        }
        return -1;
    }

    private Command parseExpression(String[] tokens) {
        return null;
    }

    private Function parse(String[] tokens, String namespace, String funcName, Map<String, Object> scopeObjs) {
        System.out.println("PARSING: "+funcName);
        System.out.println("TOKENLIST: "+Arrays.toString(tokens));
        Map<String, Object> objects = new HashMap<>(scopeObjs);
        List<Command> commands = new ArrayList<>();
        int controlNum = 1;
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            System.out.println(token);
            if(token.equals("function")) {
                if(tokens[i+1].matches(Regex.VARNAME_REGEX) && !tokens[i+1].matches(Regex.RESERVED_REGEX)
                        && tokens[i+2].equals("(")) {
                    int j = i+3;
                    Map<String, Object> vars = new HashMap<>();
                    int lookingFor = 0;
                    while(!tokens[j].equals(")")) {
                        System.out.println(" - "+tokens[j]);
                        if(!tokens[j].matches(Regex.VARTYPE_REGEX) && !tokens[j].matches(Regex.VARNAME_REGEX) && !tokens[j].equals(",")) {
                            throw new RuntimeException("Could not compile!");
                        } else if (tokens[j].matches(Regex.VARTYPE_REGEX) && lookingFor == 0) {
                            lookingFor++;
                        } else if (tokens[j].matches(Regex.VARNAME_REGEX) && lookingFor == 1) {
                            lookingFor++;
                            Variable var = new Variable(VarType.fromString(tokens[j-1]));
                            vars.put(tokens[j], var);
                        } else if (tokens[j].equals(",") && lookingFor == 2) {
                            lookingFor = 0;
                        } else {
                            throw new RuntimeException("Could not compile!");
                        }
                        j++;
                    }
                    Map<String, Object> newScopeObjs = new HashMap<>(objects);
                    newScopeObjs.putAll(vars);
                    String[] funcTokens = Arrays.copyOfRange(tokens, j+2, tokens.length);
                    int endOfScope = findEndOfScope(funcTokens);
                    funcTokens = Arrays.copyOfRange(funcTokens, 0, endOfScope);
                    Function func = parse(funcTokens, namespace, tokens[i+1], newScopeObjs);
                    objects.put(tokens[i+1], func);
                    i += endOfScope;
                } else {
                    throw new RuntimeException("Could not compile!");
                }
            } else if (token.matches(Regex.VARTYPE_REGEX)) {
                if(tokens[i+1].matches(Regex.VARNAME_REGEX) && tokens[i+2].equals("=")) {
                    // TODO INIT VARIABLE WITH VALUE
                    if(tokens[i+3].equals("{")) {
                        String[] nbtTokens = Arrays.copyOfRange(tokens, i+4, tokens.length);
                        int endOfScope = findEndOfScope(nbtTokens);
                        nbtTokens = Arrays.copyOfRange(tokens, i+3, i+4+endOfScope+1);
                        CompoundTag nbtTag = CompoundTag.fromTokens(nbtTokens, scopeObjs);
                        objects.put(token, nbtTag);
                        System.out.println(objects);
                        i+=4+endOfScope;
                    } else  {
                        String[] statement = Arrays.copyOfRange(tokens, i+4, tokens.length);
                        int endOfScope = findEndOfStatement(statement);
                        statement = Arrays.copyOfRange(statement, i+3, i+4+endOfScope+1);
                        // TODO PARSE STATEMENT
                    }
                } else if (tokens[i+1].matches(Regex.VARNAME_REGEX) && tokens[i+2].equals(";")) {
                    // TODO INIT VARIABLE NULL
                }
            } else if (token.equals("if")) {
                if(tokens[i+1].equals("(")) {
                    String[] expTokens = Arrays.copyOfRange(tokens, i+2, tokens.length);
                    int endOfScope = findEndOfExpression(expTokens);
                    expTokens = Arrays.copyOfRange(expTokens, 0, endOfScope);
                    i+=2+endOfScope;
                    String[] funcTokens = Arrays.copyOfRange(tokens, i+2, tokens.length);
                    int endOfFunc = findEndOfScope(funcTokens);
                    funcTokens = Arrays.copyOfRange(funcTokens, 0, endOfFunc);
                    i+=endOfFunc+1;
                    Map<String, Object> newScopeObjs = new HashMap<>(objects);
                    Function block = parse(funcTokens, namespace, funcName+"_if"+controlNum, newScopeObjs);
                    block.write();
                    Command command = new Command("");
                    controlNum++;
                }
            } else if (token.matches(Regex.VARNAME_REGEX)) {
                if(!objects.containsKey(token)) {
                    throw new RuntimeException(String.format("Object '%s' is not defined!", token));
                }
                if(tokens[i+1].equals("(")) {
                    // TODO CALL FUNCTION
                } else {
                    // TODO VARIABLE
                }
            } else {
                throw new RuntimeException("Could not compile!");
            }
        }
        return null;
    }

    public boolean compile() {
        List<String> tokens = lex();
        String[] tokenArr = tokens.toArray(new String[]{});
        parse(tokenArr, "std", "__global__", new HashMap<>());
        return true;
    }

}
