package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class AddInstructionTest {
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
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }
  @Test
  void executeValidThree() {
    registers.set(EAX, -5);
    registers.set(EBX, 0);
    Instruction instruction = new AddInstruction("f3", EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-5, machine.getRegisters().get(EAX));
  }
  @Test
  void executeValidFour() {
    registers.set(EAX, -5);
    registers.set(EBX, -5);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-10, machine.getRegisters().get(EAX));
  }
  @Test
  void toStringValidOne() {
    registers.set(EAX, -5);
    registers.set(EBX, -5);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    Assertions.assertEquals("add EAX EBX", instruction.toString());
  }
  @Test
  void toStringValidTwoWithLabel() {
    registers.set(EAX, -5);
    registers.set(EBX, -5);
    Instruction instruction = new AddInstruction("f2", EAX, EBX);
    Assertions.assertEquals("f2: add EAX EBX", instruction.toString());
  }

  @Test
  void equalsValid() {
    registers.set(EAX, -5);
    registers.set(EBX, -5);
    Instruction instruction = new AddInstruction("f2", EAX, EBX);
    Instruction instruction2 = new AddInstruction("f2", EAX, EBX);
    Assertions.assertEquals(true, instruction.equals(instruction2));
  }

  @Test
  void equalsInvalid1() {
    registers.set(EAX, -5);
    registers.set(EBX, -5);
    Instruction instruction = new AddInstruction("f2", EAX, EBX);
    Instruction instruction2 = new DivInstruction("f2", EAX, EBX);
    Assertions.assertEquals(false, instruction.equals(instruction2));
  }
  @Test
  void equalsInvalid2() {
    registers.set(EAX, -5);
    registers.set(EBX, -5);
    registers.set(ECX, -5);
    registers.set(EDX, -5);
    Instruction instruction = new AddInstruction("f2", EAX, EBX);
    Instruction instruction2 = new AddInstruction("f2", ECX, EDX);
    Assertions.assertEquals(false, instruction.equals(instruction2));
  }
  @Test
  void equalsInvalid3() {
    registers.set(EAX, -5);
    registers.set(EBX, -5);
    Instruction instruction = new AddInstruction("f2", EAX, EBX);
    Instruction instruction2 = new AddInstruction("f3", EAX, EBX);
    Assertions.assertEquals(false, instruction.equals(instruction2));
  }

}

