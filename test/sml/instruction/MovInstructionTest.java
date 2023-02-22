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
        String numberToAdd = "5";
        String result = "EAX";
        Instruction instruction = new MovInstruction(null, result, numberToAdd);
        instruction.execute(machine);
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        String numberToAdd = "-5";
        String result = "ECX";
        Instruction instruction = new MovInstruction(null, result, numberToAdd);
        instruction.execute(machine);
        Assertions.assertEquals(-5, machine.getRegisters().get(ECX));
    }
    @Test
    void toStringValidOne() {
        String numberToAdd = "-5";
        String result = "EAX";
        Instruction instruction = new MovInstruction(null, result, numberToAdd);
        Assertions.assertEquals("mov EAX -5", instruction.toString());
    }
    @Test
    void toStringValidTwoWithLabel() {
        String numberToAdd = "-5";
        String result = "EAX";
        Instruction instruction = new MovInstruction("f2", result, numberToAdd);
        Assertions.assertEquals("f2: mov EAX -5", instruction.toString());
    }
}
