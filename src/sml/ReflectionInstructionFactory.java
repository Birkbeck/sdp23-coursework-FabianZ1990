package sml;

import sml.instruction.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class ReflectionInstructionFactory {


public Instruction createInstruction(String opcode, String label, String result, String source) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

    if (InstructionList.containsOpcode(opcode)) {

        Constructor<?>[] cons = InstructionList.classFromOpcode(opcode).getDeclaredConstructors();

        //Constructor<?> cons= classFromOpcode(opcode).getDeclaredConstructor(String.class, String.class, String.class);
        return (Instruction) cons[0].newInstance(label, result, source);
    }
    else  {
        System.out.println("Unknown instruction: " + opcode);
    }


    return null;
}



}






