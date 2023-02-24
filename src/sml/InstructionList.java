package sml;

import sml.instruction.*;

import java.util.Map;

public class InstructionList {


    private static final Map<String, Class<?>> OPERATIONS_MAP = Map.of(
            "mov", MovInstruction.class,
            "out", OutInstruction.class,
            "jnz", JnzInstruction.class,
            "add", AddInstruction.class,
            "sub", SubInstruction.class,
            "mul", MulInstruction.class,
            "div", DivInstruction.class
    );

    public static Class<?> classFromOpcode(String opcode) {
        return OPERATIONS_MAP.get(opcode);
    }
    public static boolean containsOpcode(String opcode ) {
        return OPERATIONS_MAP.containsKey(opcode);
    }

}
