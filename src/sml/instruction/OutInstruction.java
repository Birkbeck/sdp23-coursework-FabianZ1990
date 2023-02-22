package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

public class OutInstruction extends Instruction {
    private final RegisterName result;


    public static final String OP_CODE = "out";

    public OutInstruction(String label, String result, String source) {
        super(label, OP_CODE);
        this.result = Registers.Register.valueOf(result);
    }

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
    protected boolean equals(Instruction ins) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}