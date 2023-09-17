package org.example;

import java.util.ArrayList;
import java.util.List;

public class WhitespaceInstruction {
    WhitespaceInstructionType type;
    int valuei;
    String values;

    public WhitespaceInstruction(WhitespaceInstructionType type, int valuei) {
        this.type = type;
        this.valuei = valuei;
    }

    public WhitespaceInstruction(WhitespaceInstructionType type, String values) {
        this.type = type;
        this.values = values;
    }

    public WhitespaceInstruction(WhitespaceInstructionType whitespaceInstructionType) {
        this.type = whitespaceInstructionType;
    }

    public static List<WhitespaceInstruction> parseList(String wsSTL) {
        List<WhitespaceInstruction> instructions = new ArrayList<>();
        if (wsSTL == null || wsSTL.isEmpty()) {
            return instructions;
        }

        int current = 0;
        System.out.println(wsSTL.length());
        while (current < wsSTL.length()) {
            if (wsSTL.startsWith("SS", current)) {
                current += 2;
                List<Integer> digits = new ArrayList<>();
                while (current < wsSTL.length() && wsSTL.charAt(current) != 'L') {
                    if (wsSTL.charAt(current) == 'S') {
                        digits.add(0);
                    } else if (wsSTL.charAt(current) == 'T') {
                        digits.add(1);
                    }
                    current++;
                }
                int num = 0;
                for (int i = 1; i < digits.size(); ++i) {
                    num = num * 2 + digits.get(i);
                }
                if (digits.get(0) == 1) num = -num;
                current++;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.PUSH, num));
            } else if (wsSTL.startsWith("SLS", current)) {
                current += 3;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.DUPLICATE));
            } else if (wsSTL.startsWith("STS", current)) {
                current += 3;
                List<Integer> digits = new ArrayList<>();
                while (current < wsSTL.length() && wsSTL.charAt(current) != 'L') {
                    if (wsSTL.charAt(current) == 'S') {
                        digits.add(0);
                    } else if (wsSTL.charAt(current) == 'T') {
                        digits.add(1);
                    }
                    current++;
                }
                int num = 0;
                for (int i = 1; i < digits.size(); ++i) {
                    num = num * 2 + digits.get(i);
                }
                if (digits.get(0) == 1) num = -num;
                current++;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.REFERENCE, num));
            } else if (wsSTL.startsWith("STL", current)) {
                current += 3;
                List<Integer> digits = new ArrayList<>();
                while (current < wsSTL.length() && wsSTL.charAt(current) != 'L') {
                    if (wsSTL.charAt(current) == 'S') {
                        digits.add(0);
                    } else if (wsSTL.charAt(current) == 'T') {
                        digits.add(1);
                    }
                    current++;
                }
                int num = 0;
                for (int i = 1; i < digits.size(); ++i) {
                    num = num * 2 + digits.get(i);
                }
                if (digits.get(0) == 1) num = -num;
                current++;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.SLIDE, num));
            } else if (wsSTL.startsWith("SLT", current)) {
                current += 3;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.SWAP));
            } else if (wsSTL.startsWith("SLL", current)) {
                current += 3;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.DISCARD));
            } else if (wsSTL.startsWith("TSSS", current)) {
                current += 4;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.PLUS));
            } else if (wsSTL.startsWith("TSST", current)) {
                current += 4;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.MINUS));
            } else if (wsSTL.startsWith("TSSL", current)) {
                current += 4;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.MULTIPLY));
            } else if (wsSTL.startsWith("TSTS", current)) {
                current += 4;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.DIVIDE));
            } else if (wsSTL.startsWith("TSTT", current)) {
                current += 4;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.MODULO));
            } else if (wsSTL.startsWith("TTS", current)) {
                current += 3;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.STORE));
            } else if (wsSTL.startsWith("TTT", current)) {
                current += 3;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.RETRIEVE));
            } else if (wsSTL.startsWith("LSS", current)) {
                current += 3;
                StringBuilder s = new StringBuilder();
                while (current < wsSTL.length() && wsSTL.charAt(current) != 'L') {
                    int charSize = 8;
                    List<Integer> digits = new ArrayList<>();
                    for (int i = 0; i < charSize; i++) {
                        if (wsSTL.charAt(current) == 'S') {
                            digits.add(0);
                        } else if (wsSTL.charAt(current) == 'T') {
                            digits.add(1);
                        }
                        current++;
                        if (current >= wsSTL.length() || wsSTL.charAt(current) == 'L') {
                            break;
                        }
                    }
                    int num = 0;
                    for (Integer digit : digits) {
                        num = num * 2 + digit;
                    }
                    s.append((char) num);
                }
                current++;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.MARK, s.toString()));
            } else if (wsSTL.startsWith("LST", current)) {
                current += 3;
                StringBuilder s = new StringBuilder();
                while (current < wsSTL.length() && wsSTL.charAt(current) != 'L') {
                    int charSize = 8;
                    List<Integer> digits = new ArrayList<>();
                    for (int i = 0; i < charSize; i++) {
                        if (wsSTL.charAt(current) == 'S') {
                            digits.add(0);
                        } else if (wsSTL.charAt(current) == 'T') {
                            digits.add(1);
                        }
                        current++;
                        if (current >= wsSTL.length() || wsSTL.charAt(current) == 'L') {
                            break;
                        }
                    }
                    int num = 0;
                    for (Integer digit : digits) {
                        num = num * 2 + digit;
                    }
                    s.append((char) num);
                }
                current++;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.CALL, s.toString()));
            } else if (wsSTL.startsWith("LSL", current)) {
                current += 3;
                StringBuilder s = new StringBuilder();
                while (current < wsSTL.length() && wsSTL.charAt(current) != 'L') {
                    int charSize = 8;
                    List<Integer> digits = new ArrayList<>();
                    for (int i = 0; i < charSize; i++) {
                        if (wsSTL.charAt(current) == 'S') {
                            digits.add(0);
                        } else if (wsSTL.charAt(current) == 'T') {
                            digits.add(1);
                        }
                        current++;
                        if (current >= wsSTL.length() || wsSTL.charAt(current) == 'L') {
                            break;
                        }
                    }
                    int num = 0;
                    for (Integer digit : digits) {
                        num = num * 2 + digit;
                    }
                    s.append((char) num);
                }
                current++;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.JUMP, s.toString()));
            } else if (wsSTL.startsWith("LTS", current)) {
                current += 3;
                StringBuilder s = new StringBuilder();
                while (current < wsSTL.length() && wsSTL.charAt(current) != 'L') {
                    int charSize = 8;
                    List<Integer> digits = new ArrayList<>();
                    for (int i = 0; i < charSize; i++) {
                        if (wsSTL.charAt(current) == 'S') {
                            digits.add(0);
                        } else if (wsSTL.charAt(current) == 'T') {
                            digits.add(1);
                        }
                        current++;
                        if (current >= wsSTL.length() || wsSTL.charAt(current) == 'L') {
                            break;
                        }
                    }
                    int num = 0;
                    for (Integer digit : digits) {
                        num = num * 2 + digit;
                    }
                    s.append((char) num);
                }
                current++;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.JUMP_IF_ZERO, s.toString()));
            } else if (wsSTL.startsWith("LTT", current)) {
                current += 3;
                StringBuilder s = new StringBuilder();
                while (current < wsSTL.length() && wsSTL.charAt(current) != 'L') {
                    int charSize = 8;
                    List<Integer> digits = new ArrayList<>();
                    for (int i = 0; i < charSize; i++) {
                        if (wsSTL.charAt(current) == 'S') {
                            digits.add(0);
                        } else if (wsSTL.charAt(current) == 'T') {
                            digits.add(1);
                        }
                        current++;
                        if (current >= wsSTL.length() || wsSTL.charAt(current) == 'L') {
                            break;
                        }
                    }
                    int num = 0;
                    for (Integer digit : digits) {
                        num = num * 2 + digit;
                    }
                    s.append((char) num);
                }
                current++;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.JUMP_IF_NEGATIVE, s.toString()));
            } else if (wsSTL.startsWith("LTL", current)) {
                current += 3;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.RETURN));
            } else if (wsSTL.startsWith("LLL", current)) {
                current += 3;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.END));
            } else if (wsSTL.startsWith("TLSS", current)) {
                current += 4;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.OUTPUT_CHARACTER));
            } else if (wsSTL.startsWith("TLST", current)) {
                current += 4;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.OUTPUT_NUMBER));
            } else if (wsSTL.startsWith("TLTS", current)) {
                current += 4;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.READ_CHARACTER));
            } else if (wsSTL.startsWith("TLTT", current)) {
                current += 4;
                instructions.add(new WhitespaceInstruction(WhitespaceInstructionType.READ_NUMBER));
            } else {
                throw new RuntimeException("Invalid instruction:" + current);
            }
        }
        return instructions;
    }
}
