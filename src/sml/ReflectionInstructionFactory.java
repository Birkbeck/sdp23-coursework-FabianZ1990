package sml;

import sml.instruction.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ReflectionInstructionFactory {


public Instruction createInstruction(String opcode, String label, String result, String source) throws InvocationTargetException, InstantiationException, IllegalAccessException {

    if (OPERATIONS_MAP.containsKey(opcode)) {
        Constructor[] testCons = classFromOpcode(opcode).getConstructors();
        for (Constructor cons : testCons)
            return (Instruction) cons.newInstance(label, result, source);
    }
    else  {
        System.out.println("Unknown instruction: " + opcode);
    }


    return null;
}


    private static final Map<String, Class<?>> OPERATIONS_MAP = Map.of(
            "mov", MovInstruction.class,
            "out", OutInstruction.class,
            "jnz", JnzInstruction.class,
            "add", AddInstruction.class,
            "sub", SubInstruction.class,
            "mul", MulInstruction.class,
            "div", DivInstruction.class
    );

    private static Class<?> classFromOpcode(String op) {
        return OPERATIONS_MAP.get(op);
    }

}






