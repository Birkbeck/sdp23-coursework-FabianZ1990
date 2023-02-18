package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class MovInstructionTest {
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
        int numberToAdd = 5;
        Instruction instruction = new MovInstruction(null, EAX, numberToAdd);
        instruction.execute(machine);
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        int numberToAdd = -5;
        Instruction instruction = new MovInstruction(null, ECX, numberToAdd);
        instruction.execute(machine);
        Assertions.assertEquals(-5, machine.getRegisters().get(ECX));
    }
    @Test
    void toStringValidOne() {
        int numberToAdd = -5;
        Instruction instruction = new MovInstruction(null, EAX, numberToAdd);
        Assertions.assertEquals("mov EAX -5", instruction.toString());
    }
    @Test
    void toStringValidTwoWithLabel() {
        int numberToAdd = -5;
        Instruction instruction = new MovInstruction("f2", EAX, numberToAdd);
        Assertions.assertEquals("f2: mov EAX -5", instruction.toString());
    }
}
