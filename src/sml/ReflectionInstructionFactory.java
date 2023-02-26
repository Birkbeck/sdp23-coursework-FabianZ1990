package sml;

import sml.instruction.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

public class ReflectionInstructionFactory {

private static ReflectionInstructionFactory factory;


private ReflectionInstructionFactory() {

}

public static ReflectionInstructionFactory getInstance()
{
    if (factory == null)

        factory = new ReflectionInstructionFactory();
        return factory;

}

public Instruction createInstruction(String opcode, Object [] args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException {

    if (InstructionList.containsOpcode(opcode)) {

        String instructionFromOpcode = "sml.instruction." + opcode.substring(0,1).toUpperCase() + opcode.substring(1) + "Instruction";
        Constructor<?>[] cons = Class.forName(instructionFromOpcode).getDeclaredConstructors();
//        ArrayList<Object> parameterInput = new ArrayList<>();
//        parameterInput.add(label);
//        parameterInput.add(result);
//        parameterInput.add(source);
//
//        Class[] constructorParameters = cons[0].getParameterTypes();
//
//        if (constructorParameters[0].getName() == "java.lang.String" )  {
//
//        }

//        for (Class con : a) {
//            System.out.println(con.getName());}

        //Constructor<?>[] cons = InstructionList.classFromOpcode(opcode).getDeclaredConstructors();
        //Constructor<?> cons= classFromOpcode(opcode).getDeclaredConstructor(String.class, String.class, String.class);
        return (Instruction) cons[1].newInstance(args);
    }
    else  {
        System.out.println("Unknown instruction: " + opcode);
    }


    return null;
}



}






