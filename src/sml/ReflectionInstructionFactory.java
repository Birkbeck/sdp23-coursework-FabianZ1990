package sml;

import sml.instruction.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ReflectionInstructionFactory {


public Instruction createInstruction(String opcode, String label, String result, String source) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

    if (OPERATIONS_MAP.containsKey(opcode)) {

        Constructor<?>[] cons = classFromOpcode(opcode).getDeclaredConstructors();

        //Constructor<?> cons= classFromOpcode(opcode).getDeclaredConstructor(String.class, String.class, String.class);
        return (Instruction) cons[0].newInstance(label, result, source);
    }
    else  {
        System.out.println("Unknown instruction: " + opcode);
    }


    return null;
}


    public static final Map<String, Class<?>> OPERATIONS_MAP = Map.of(
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






