package sml.instruction;

import sml.Exceptions.LabelNotFoundException;
import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import java.util.Objects;


/**
 * Takes  a register, as well as a label. In case the input register does not hold the value "0", the program jumps to the line
 * marked by the label of the second input. In case the register contains 0, the program continues normally with the next line.
 * Can take an optional label, in case the instruction line is marked by one (value label can be null).
 * @author Fabian Zischler
 */


public class JnzInstruction extends Instruction{
    private final RegisterName result;
    private final String nextLabel;

    public static final String OP_CODE = "jnz";

    /**
     * Constructor: a String with a label (can be null) and a register which value is checked, and a String with a label for a potential jump.
     *
     * @param label optional label (can be null)
     * @param result first register. Will be checked for value of 0, if higher, a jump to label "labelToJump" is executed. If value is 0
     *               program will continue normally (with next line)
     * @param labelToJump label to which line the program will jump if the value in result is not 0.
     */

    public JnzInstruction(String label, RegisterName result, String labelToJump) {
        super(label, OP_CODE);
        this.result = result;
        this.nextLabel = labelToJump;
    }

    /**
     * Executes the arithmetic instruction, sets the registers to new values and returns a program counter update.
     * As this is a jump instruction, the program counter value that is returned can be different from the normal update.
     *
     * @param m Machine file that contains an Instruction of that type.
     */

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
        return Objects.hash(OP_CODE, result, nextLabel);
    }
}