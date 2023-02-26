package sml;

import sml.Exceptions.OpcodeNotFoundException;
import sml.instruction.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

import static java.lang.System.exit;

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

public Instruction createInstruction(String opcode, ArrayList<String> args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, OpcodeNotFoundException {


    String instructionFromOpcode = "sml.instruction." + opcode.substring(0,1).toUpperCase() + opcode.substring(1) + "Instruction";


    Constructor<?>[] cons;

    try {
        cons = Class.forName(instructionFromOpcode).getDeclaredConstructors();
    } catch (ClassNotFoundException e) {
        throw new OpcodeNotFoundException("Unknown instruction: "  + opcode +  " - The program will be terminated.");
    }

    ArrayList<Object> passOnParameters = new ArrayList<>();

        passOnParameters.add(args.get(0));                                                                              //label has to be added as a string even if it is numeric

        Class[] constructorParameters = cons[0].getParameterTypes();

        for (int x = 1; x < constructorParameters.length; x++) {
              if (constructorParameters[x].getName().equals("sml.RegisterName")) {
                passOnParameters.add(Registers.Register.valueOf(args.get(x)));
              }
              else if ((constructorParameters[x].getName().equals("int") && NumericCheck.isNumeric(args.get(x))))   {
                  passOnParameters.add(Integer.parseInt(args.get(x)));
              }
              else {
                  passOnParameters.add(args.get(x));
          }

       }

        return (Instruction) cons[0].newInstance(passOnParameters.toArray());
    }
//    else  {
//
//        throw new OpcodeNotFoundException("Unknown instruction: "  + opcode +  " - The program will be terminated.");
//

    }










