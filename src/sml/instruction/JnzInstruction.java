package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class JnzInstruction extends Instruction {
    private final RegisterName result;
    private final String nextLabel;

    public static final String OP_CODE = "jnz";

    public JnzInstruction(String label, String result, String labelToJump) {
        super(label, OP_CODE);
        this.result = Registers.Register.valueOf(result);
        this.nextLabel = labelToJump;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        if (value1 == 0) {
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        } else {
            return m.getLabels().getAddress(this.nextLabel);
        }
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + nextLabel;
    }

    @Override
    protected boolean equals(Instruction ins) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}