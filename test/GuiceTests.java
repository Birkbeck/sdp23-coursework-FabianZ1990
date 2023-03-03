import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.*;
import sml.Exceptions.OpcodeNotFoundException;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;

import static sml.Registers.Register.*;

class GuiceTests {
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
        Injector testInjector = Guice.createInjector(new GuiceModule());
        guiceInterface testGuiceFac = testInjector.getInstance(guiceInterface.class);
        Instruction instruction;
        try {
            instruction = testGuiceFac.buildFactory().createInstruction(testOpcode, testInput);
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
        Injector testInjector = Guice.createInjector(new GuiceModule());
        guiceInterface testGuiceFac = testInjector.getInstance(guiceInterface.class);
        Instruction instruction;
        try {
            instruction = testGuiceFac.buildFactory().createInstruction(testOpcode, testInput);
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 OpcodeNotFoundException e) {
            throw new RuntimeException(e);
        }
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }
}
