import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Exceptions.OpcodeNotFoundException;
import sml.Instruction;
import sml.Machine;
import sml.ReflectionInstructionFactory;
import sml.Registers;
import sml.instruction.AddInstruction;

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
        Instruction instruction = null;
        try {
            instruction = testFac.createInstruction(testOpcode, testInput);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (OpcodeNotFoundException e) {
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
        Instruction instruction = null;
        try {
            instruction = testFac.createInstruction(testOpcode, testInput);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (OpcodeNotFoundException e) {
            throw new RuntimeException(e);
        }
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

}
