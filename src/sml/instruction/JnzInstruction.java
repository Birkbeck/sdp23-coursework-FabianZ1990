package sml.instruction;

import sml.Exceptions.LabelNotFoundException;
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


    public JnzInstruction(String label, RegisterName result, String labelToJump) {
        super(label, OP_CODE);
        this.result = result;
        this.nextLabel = labelToJump;
    }

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
            try {
                return m.getLabels().getAddress(this.nextLabel);
            } catch (LabelNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + nextLabel;
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        else if (otherObject instanceof JnzInstruction other)
            return
                    this.nextLabel.equals(other.nextLabel) && this.result.equals(other.result) && this.opcode.equals(other.opcode) && this.label.equals(other.label);
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}