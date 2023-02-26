package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import sml.Registers;

import java.util.Objects;

public class SubInstruction extends Instruction {

    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "sub";


    public SubInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    public SubInstruction(String label, String result, String source) {
        super(label, OP_CODE);
        this.result = Registers.Register.valueOf(result);
        this.source = Registers.Register.valueOf(source);
    }
    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 - value2);
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
        else if (otherObject instanceof SubInstruction other)
            return
                    this.source.equals(other.source) && this.result.equals(other.result) && this.opcode.equals(other.opcode) && this.label.equals(other.label);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(OP_CODE, result, source);
    }
}

