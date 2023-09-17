package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String ws = "   \t\t  \t  \n\t\n     \t    \t\t\n\t\n     \t\t \t\t\t\t\n\t\n     \t\t  \t  \n\t\n     \t\t  \t \t\n\t\n      \t     \n\t\n     \t \t \t\t\t\n\t\n     \t\t \t   \n\t\n     \t\t \t  \t\n\t\n     \t\t\t \t  \n\t\n     \t\t  \t \t\n\t\n     \t\t\t  \t\t\n\t\n     \t\t\t    \n\t\n     \t\t    \t\n\t\n     \t\t   \t\t\n\t\n     \t\t  \t \t\n\t\n  \n\n\n";
        WhitespaceParser parser = new WhitespaceParser(ws);

        String wsSTL = parser.parse();

        List<WhitespaceInstruction> instructions = WhitespaceInstruction.parseList(wsSTL);

        for (WhitespaceInstruction instruction : instructions) {
            System.out.print(instruction.type.toString() + " " + instruction.valuei + " " + instruction.values + "\n");
        }
    }
}