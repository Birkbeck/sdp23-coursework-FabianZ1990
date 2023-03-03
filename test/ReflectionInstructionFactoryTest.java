import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Exceptions.OpcodeNotFoundException;
import sml.Instruction;
import sml.Machine;
import sml.ReflectionInstructionFactory;
import sml.Registers;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import static sml.Registers.Register.*;

class ReflectionInstructionFactoryTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        String testOpcode = "add";
        ArrayList<String> testInput = new ArrayList<>(Arrays.asList(null, "EAX", "EBX"));
        ReflectionInstructionFactory testFac = ReflectionInstructionFactory.getInstance();
        Instruction instruction;
        try {
            instruction = testFac.createInstruction(testOpcode, testInput);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 OpcodeNotFoundException e) {
            throw new RuntimeException(e);
        }
        instruction.execute(machine);
        Assertions.assertEquals(11, machine.getRegisters().get(EAX));
    }


    @Test
    void executeValid2() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        String testOpcode = "mul";
        ArrayList<String> testInput = new ArrayList<>(Arrays.asList("f2", "EAX", "EBX"));
        ReflectionInstructionFactory testFac = ReflectionInstructionFactory.getInstance();
        Instruction instruction;
        try {
            instruction = testFac.createInstruction(testOpcode, testInput);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 OpcodeNotFoundException e) {
            throw new RuntimeException(e);
        }
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }
    @Test
    void executeValid3() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        String testOpcode = "mov";
        ArrayList<String> testInput = new ArrayList<>(Arrays.asList("f2", "EAX", "1"));
        ReflectionInstructionFactory testFac = ReflectionInstructionFactory.getInstance();
        Instruction instruction;
        try {
            instruction = testFac.createInstruction(testOpcode, testInput);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 OpcodeNotFoundException e) {
            throw new RuntimeException(e);
        }
        instruction.execute(machine);
        Assertions.assertEquals(1, machine.getRegisters().get(EAX));
    }
    @Test
    void executeValid4() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        String testOpcode = "out";
        ArrayList<String> testInput = new ArrayList<>(Arrays.asList("f2", "EAX", "1"));
        ReflectionInstructionFactory testFac = ReflectionInstructionFactory.getInstance();
        Instruction instruction;
        try {
            instruction = testFac.createInstruction(testOpcode, testInput);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 OpcodeNotFoundException e) {
            throw new RuntimeException(e);
        }
        instruction.execute(machine);
        Assertions.assertEquals("The current value stored in register EAX is 5",
                "The current value stored in register " + EAX.name() + " is " + machine.getRegisters().get(EAX));
    }

    @Test
    void tryInvalidOpcode() {
        Exception possibleException = Assertions.assertThrows(OpcodeNotFoundException.class, () ->
        {  registers.set(EAX, 5);
            registers.set(EBX, 6);
            String testOpcode = "wrongOpcode";
            ArrayList<String> testInput = new ArrayList<>(Arrays.asList("f2", "EAX", "1"));
            ReflectionInstructionFactory testFac = ReflectionInstructionFactory.getInstance();
            Instruction instruction;
            try {
                instruction = testFac.createInstruction(testOpcode, testInput);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                     OpcodeNotFoundException e) {
                throw new OpcodeNotFoundException("Unknown instruction: " + testOpcode + " - The program will be terminated.");
            }
            instruction.execute(machine);});
        Assertions.assertEquals("Unknown instruction: wrongOpcode - The program will be terminated.", possibleException.getMessage());
    }

    @Test
    void tryInvalidParameters() {
        Exception possibleException = Assertions.assertThrows(IllegalArgumentException.class, () ->
        {  registers.set(EAX, 5);
            registers.set(EBX, 6);
            String testOpcode = "mov";
            ArrayList<String> testInput = new ArrayList<>(Arrays.asList("f2", "EAX", "EBX"));
            ReflectionInstructionFactory testFac = ReflectionInstructionFactory.getInstance();
            Instruction instruction;
            try {
                instruction = testFac.createInstruction(testOpcode, testInput);
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                     OpcodeNotFoundException e) {
                throw new RuntimeException(e);
            }
            instruction.execute(machine);});
        Assertions.assertEquals("Wrong parameter provided for Instruction mov - int required", possibleException.getMessage());
    }

}
