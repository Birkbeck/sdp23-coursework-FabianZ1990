package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final int source;

    public static final String OP_CODE = "mov";

    public MovInstruction(String label, String result, String source) {
        super(label, OP_CODE);
        this.result = Registers.Register.valueOf(result);
        this.source = Integer.valueOf(source);
    }

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
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}