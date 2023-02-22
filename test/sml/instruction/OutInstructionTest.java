package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class OutInstructionTest {
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
        Instruction instruction = new OutInstruction(null, result, source);
        instruction.execute(machine);
        Assertions.assertEquals("The current value stored in register EAX is 5",
                "The current value stored in register " + EAX.name() + " is " + machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        String result = "EAX";
        String source = "EBX";
        Instruction instruction = new OutInstruction(null, result, source);
        instruction.execute(machine);
        Assertions.assertEquals("The current value stored in register EAX is -5",
                "The current value stored in register " + EAX.name() + " is " + machine.getRegisters().get(EAX));
    }
    @Test
    void toStringValidOne() {
        registers.set(EAX, -5);
        registers.set(EBX, -5);
        String result = "EAX";
        String source = "EBX";
        Instruction instruction = new OutInstruction(null, result, source);
        Assertions.assertEquals("out EAX", instruction.toString());
    }
    @Test
    void toStringValidTwoWithLabel() {
        registers.set(EAX, -5);
        registers.set(EBX, -5);
        String result = "EAX";
        String source = "EBX";
        Instruction instruction = new OutInstruction("f2", result, source);
        Assertions.assertEquals("f2: out EAX", instruction.toString());
    }
}