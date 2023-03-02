package sml;

import sml.Exceptions.OpcodeNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


/**
 * creates an SML instruction from a provided opcode, as well as a list of arguments from the input file
 * Uses string creation and Reflection API to get the right instruction (needs uniform naming of subclasses of "Instruction" in the class hierarchy)
 * Scans for constructor arguments and passes on variables with the right type.
 *
 * @author Fabian Zischler
 */


public class ReflectionInstructionFactory {


    private static ReflectionInstructionFactory factory;


    /**
     * Constructor:*
     * no input variables, private because of Singleton use
     *
     */
    private ReflectionInstructionFactory() {
    }

    /**
     * Checks for an instance of the factory (if not there, initialize one)
     */
    public static ReflectionInstructionFactory getInstance() {
        if (factory == null)

            factory = new ReflectionInstructionFactory();
        return factory;

    }

    /**
     * Takes an opcode and builds the fully qualified class name of a subclass of Instruction out of it.
     * Checks the main constructor of this class for number and type of arguments.
     * Then checks the input parameters and if necessary type casts them to the required type (normally String, int or RegisterName)
     * Returns a subclass of instruction to execute the desired operation.
     *
     * @param opcode Opcode to identify instruction
     * @param args Arraylist of Strings with label (can be null) and parameters of instruction
     * @return A subclass of Instruction depending on the opcode with the given parameters
     *
     */

    public Instruction createInstruction(String opcode, ArrayList<String> args) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException, OpcodeNotFoundException {


        String instructionFromOpcode = "sml.instruction." + opcode.substring(0, 1).toUpperCase() + opcode.substring(1) + "Instruction";


        Constructor<?>[] cons;

        try {
            cons = Class.forName(instructionFromOpcode).getDeclaredConstructors();
        } catch (ClassNotFoundException e) {
            throw new OpcodeNotFoundException("Unknown instruction: " + opcode + " - The program will be terminated.");
        }

        ArrayList<Object> passOnParameters = new ArrayList<>();

        passOnParameters.add(args.get(0));                                                                              //label has to be added as a string even if it is numeric

        Class<?>[] constructorParameters = cons[0].getParameterTypes();

        for (int x = 1; x < constructorParameters.length; x++) {
            if (constructorParameters[x].getName().equals("sml.RegisterName")) {
                passOnParameters.add(Registers.Register.valueOf(args.get(x)));
            } else if ((constructorParameters[x].getName().equals("int") && NumericCheck.isNumeric(args.get(x)))) {
                passOnParameters.add(Integer.parseInt(args.get(x)));
            } else if ((constructorParameters[x].getName().equals("java.lang.String"))) {
                passOnParameters.add(args.get(x));
            } else {
                throw new IllegalArgumentException("Wrong parameter provided for Instruction "+  opcode + " - " + constructorParameters[x].getName() + " required");
            }

        }

        return (Instruction) cons[0].newInstance(passOnParameters.toArray());
    }
}











