package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;

/**
 * Takes a register, as well as an integer. The integer is stored in the register. The previously hold value is overwritten.
 * Can take an optional label, in case the instruction line is marked by one (value label can be null).
 * @author Fabian Zischler
 */


public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final int source;

    public static final String OP_CODE = "mov";


    /**
     * Constructor: a String with a label (can be null), as well as two registers on which values the arithmetic instruction is performed
     *
     * @param label optional label (can be null)
     * @param result first register. Value from source (int) will be stored in this register
     * @param source Integer number. Value will be stored in register result.
     */

    public MovInstruction(String label, RegisterName result, int source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    public MovInstruction(String label, String result, String source) {
        super(label, OP_CODE);
        this.result = Registers.Register.valueOf(result);
        this.source = Integer.valueOf(source);
    }

    /**
     * Executes the loading instruction, sets the registers to new values and returns a program counter update.
     *
     * @param m Machine file that contains an Instruction of that type.
     */

    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, source);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        else if (otherObject instanceof MovInstruction other)
            return
                    this.source == other.source && this.result.equals(other.result) && this.opcode.equals(other.opcode) && this.label.equals(other.label);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(OP_CODE, result, source);
    }
}