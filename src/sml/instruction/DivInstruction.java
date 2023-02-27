package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;


/**
 * Takes 2 registers and divides the value of the first by the value of the second. Stores the result in the first of the two registers (order is determined by the SML file).
 * Uses integer division and checks for exceptions in case of division by zero
 * Can take an optional label, in case the instruction line is marked by one (value label can be null).
 * @author Fabian Zischler
 */

public class DivInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "div";

    /**
     * Constructor: a String with a label (can be null), as well as two registers on which values the arithmetic instruction is performed
     *
     * @param label optional label (can be null)
     * @param result first register. Value will be the dividend and the result will be stored in this register
     * @param source second register. Value will be the divisor. Register keeps original value after the operation.
     */

    public DivInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    public DivInstruction(String label, String result, String source) {
        super(label, OP_CODE);
        this.result = Registers.Register.valueOf(result);
        this.source = Registers.Register.valueOf(source);
    }

    /**
     * Executes the arithmetic instruction, sets the registers to new values and returns a program counter update.
     * In case of division operation, also throws exceptions when division through zero is tried.
     *
     * @param m Machine file that contains an Instruction of that type.
     */
    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        if (value2 == 0)
        {
            throw new ArithmeticException("Division by zero not possible");
        }
        else
        {
            m.getRegisters().set(result, value1 / value2);
            return NORMAL_PROGRAM_COUNTER_UPDATE;
        }
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
            else if (otherObject instanceof DivInstruction other)
                return
                        this.source.equals(other.source) && this.result.equals(other.result) && this.opcode.equals(other.opcode) && this.label.equals(other.label);
            return false;
        }


    @Override
    public int hashCode() {
        return Objects.hash(OP_CODE, result, source);
    }
}