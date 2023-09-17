package org.example;

public class WhitespaceParser {
    String ws;

    public WhitespaceParser(String ws) {
        this.ws = ws;
    }

    public void setWhitespace(String ws) {
        this.ws = ws;
    }

    public String parse() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ws.length(); i++) {
            if (ws.charAt(i) == ' ') {
                builder.append("S");
            } else if (ws.charAt(i) == '\t') {
                builder.append("T");
            } else if (ws.charAt(i) == '\n') {
                builder.append("L");
            }
        }
        return builder.toString();
    }
}
