package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class SubInstructionTest {
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
        String result = "EAX";
        String source = "EBX";
        Instruction instruction = new SubInstruction(null, result, source);
        instruction.execute(machine);
        Assertions.assertEquals(-1, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        String result = "EAX";
        String source = "EBX";
        Instruction instruction = new SubInstruction(null, result, source);
        instruction.execute(machine);
        Assertions.assertEquals(-11, machine.getRegisters().get(EAX));
    }
    @Test
    void executeValidThree() {
        registers.set(EAX, -5);
        registers.set(EBX, 0);
        String result = "EAX";
        String source = "EBX";
        Instruction instruction = new SubInstruction(null, result, source);
        instruction.execute(machine);
        Assertions.assertEquals(-5, machine.getRegisters().get(EAX));
    }
    @Test
    void executeValidFour() {
        registers.set(EAX, -5);
        registers.set(EBX, -5);
        String result = "EAX";
        String source = "EBX";
        Instruction instruction = new SubInstruction(null, result, source);
        instruction.execute(machine);
        Assertions.assertEquals(0, machine.getRegisters().get(EAX));
    }
    @Test
    void toStringValidOne() {
        registers.set(EAX, -5);
        registers.set(EBX, -5);
        String result = "EAX";
        String source = "EBX";
        Instruction instruction = new SubInstruction(null, result, source);
        Assertions.assertEquals("sub EAX EBX", instruction.toString());
    }
    @Test
    void toStringValidTwoWithLabel() {
        registers.set(EAX, -5);
        registers.set(EBX, -5);
        String result = "EAX";
        String source = "EBX";
        Instruction instruction = new SubInstruction("f2", result, source);
        Assertions.assertEquals("f2: sub EAX EBX", instruction.toString());
    }
}
