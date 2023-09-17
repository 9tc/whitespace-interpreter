package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class WhitespaceRunner {
    List<WhitespaceInstruction> instructions;
    Stack valueStack;
    Stack callStack;
    Map<Integer, Integer> heap;
    int pc;

    public WhitespaceRunner(List<WhitespaceInstruction> instructions) {
        this.instructions = instructions;
        this.valueStack = new Stack();
        this.callStack = new Stack();
        this.heap = new HashMap<Integer, Integer>();
        this.pc = 0;
    }

    int findLabel(String label) {
        for (int i = 0; i < instructions.size(); i++) {
            if (instructions.get(i).type == WhitespaceInstructionType.MARK && instructions.get(i).values.equals(label)) {
                return i;
            }
        }
        return -1;
    }

    public void run() {
        while (pc < instructions.size()) {
            WhitespaceInstruction instruction = instructions.get(pc++);
            switch (instruction.type) {
                case PUSH:
                    valueStack.push(instruction.valuei);
                    break;
                case DUPLICATE:
                    valueStack.push(valueStack.peek());
                    break;
                case REFERENCE:
                    valueStack.push(heap.get(valueStack.pop()));
                    break;
                case SLIDE:
                    int n = (int) valueStack.pop();
                    for (int i = 0; i < instruction.valuei; i++) {
                        valueStack.pop();
                    }
                    valueStack.push(n);
                    break;
                case SWAP:
                    int a = (int) valueStack.pop();
                    int b = (int) valueStack.pop();
                    valueStack.push(a);
                    valueStack.push(b);
                    break;
                case DISCARD:
                    valueStack.pop();
                    break;
                case PLUS:
                    a = (int) valueStack.pop();
                    b = (int) valueStack.pop();
                    valueStack.push(a + b);
                    break;
                case MINUS:
                    a = (int) valueStack.pop();
                    b = (int) valueStack.pop();
                    valueStack.push(b - a);
                    break;
                case MULTIPLY:
                    a = (int) valueStack.pop();
                    b = (int) valueStack.pop();
                    valueStack.push(a * b);
                    break;
                case DIVIDE:
                    a = (int) valueStack.pop();
                    b = (int) valueStack.pop();
                    valueStack.push(b / a);
                    break;
                case MODULO:
                    a = (int) valueStack.pop();
                    b = (int) valueStack.pop();
                    valueStack.push(b % a);
                    break;
                case STORE:
                    int value = (int) valueStack.pop();
                    int address = (int) valueStack.pop();
                    heap.put(address, value);
                    break;
                case RETRIEVE:
                    address = (int) valueStack.pop();
                    valueStack.push(heap.get(address));
                    break;
                case MARK:
                    break;
                case CALL:
                    callStack.push(pc);
                    pc = findLabel(instruction.values);
                    break;
                case JUMP:
                    pc = findLabel(instruction.values);
                    break;
                case JUMP_IF_ZERO:
                    if ((int) valueStack.pop() == 0) {
                        pc = findLabel(instruction.values);
                    }
                    break;
                case JUMP_IF_NEGATIVE:
                    if ((int) valueStack.pop() < 0) {
                        pc = findLabel(instruction.values);
                    }
                    break;
                case RETURN:
                    pc = (int) callStack.pop();
                    break;
                case OUTPUT_CHARACTER:
                    System.out.print((char) (int) valueStack.pop());
                    break;
                case OUTPUT_NUMBER:
                    System.out.print(valueStack.pop());
                    break;
                case READ_CHARACTER, READ_NUMBER:
                    throw new RuntimeException("Not implemented");
                case END, EXIT:
                    return;
            }
        }
    }
}
