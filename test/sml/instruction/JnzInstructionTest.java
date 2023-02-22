package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class JnzInstructionTest {
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
        machine.getLabels().addLabel("f1", 0);
        machine.getLabels().addLabel("f2", 1);
        String result = "EAX";
        String source = "EBX";
        Instruction instruction2 = new AddInstruction("f1", result, source);
        instruction2.execute(machine);
        String nextLabel = "f1";
        Instruction instruction = new JnzInstruction("f2", result, nextLabel);
        instruction.execute(machine);
        Assertions.assertEquals(0,instruction.execute(machine));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, 0);
        registers.set(EBX, 0);
        machine.getLabels().addLabel("f1", 0);
        machine.getLabels().addLabel("f2", 1);
        String result = "EAX";
        String source = "EBX";
        Instruction instruction2 = new AddInstruction("f1", result, source);
        instruction2.execute(machine);
        String nextLabel = "f1";
        Instruction instruction = new JnzInstruction("f2", result, nextLabel);
        instruction.execute(machine);
        Assertions.assertEquals(Instruction.NORMAL_PROGRAM_COUNTER_UPDATE,instruction.execute(machine));
    }
    @Test
    void toStringValidOne() {
        registers.set(EAX, -5);
        registers.set(EBX, -5);
        String nextLabel = "f1";
        String result = "EAX";
        String source = "EBX";
        Instruction instruction = new JnzInstruction(null, result, nextLabel);
        Assertions.assertEquals("jnz EAX f1", instruction.toString());
    }
    @Test
    void toStringValidTwoWithLabel() {
        registers.set(EAX, -5);
        registers.set(EBX, -5);
        String nextLabel = "f1";
        String result = "EAX";
        String source = "EBX";
        Instruction instruction = new JnzInstruction("f2", result, nextLabel);
        Assertions.assertEquals("f2: jnz EAX f1", instruction.toString());
    }
}

