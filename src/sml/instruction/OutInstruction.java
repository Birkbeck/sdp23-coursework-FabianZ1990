package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;


/**
 * Takes a register and prints it's value out to the console. Value of register is not changed.
 * Can take an optional label, in case the instruction line is marked by one (value label can be null).
 * @author Fabian Zischler
 */


public class OutInstruction extends Instruction {
    private final RegisterName result;


    public static final String OP_CODE = "out";

    /**
     * Constructor: a String with a label (can be null), as well as two registers on which values the arithmetic instruction is performed
     *
     * @param label optional label (can be null)
     * @param result first register. Value of this register will be printed out to the console. Value stays the same afterwards.
     */

    public OutInstruction(String label, RegisterName result) {
        super(label, OP_CODE);
        this.result = result;
    }

    public OutInstruction(String label, String result, String source) {
        super(label, OP_CODE);
        this.result = Registers.Register.valueOf(result);
    }
    /**
     * Executes the return instruction, prints the value of the register to the console and returns a program counter update.
     *
     * @param m Machine file that contains an Instruction of that type.
     */
    @Override
    public int execute(Machine m) {
        System.out.println("The current value stored in register " + result.name() + " is " + m.getRegisters().get(result));

        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        else if (otherObject instanceof OutInstruction other)
            return
                    this.result.equals(other.result) && this.opcode.equals(other.opcode) && this.label.equals(other.label);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(OP_CODE, result);
    }
}