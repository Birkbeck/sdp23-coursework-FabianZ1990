package sml.instruction;


import sml.Instruction;
import sml.Machine;
import sml.RegisterName;
import java.util.Objects;

/**
 * Takes 2 registers and adds their values together. Stores the result in the first of the two registers (order is determined by the SML file)
 * Can take an optional label, in case the instruction line is marked by one (value label can be null).
 *
 * @author Fabian Zischler
 */

public class AddInstruction extends Instruction {
	private final RegisterName result;
	private final RegisterName source;

	public static final String OP_CODE = "add";


	/**
	 * Constructor: a String with a label (can be null), as well as two registers on which values the arithmetic instruction is performed
	 *
	 * @param label optional label (can be null)
	 * @param result first register. Value will be added to the value of source and the result will be stored in this register
	 * @param source second register. Value will be added to the value of result. Register keeps original value after the operation.
	 */

	public AddInstruction(String label, RegisterName result, RegisterName source) {
		super(label, OP_CODE);
		this.result = result;
		this.source = source;
	}

	/**
	 * Executes the arithmetic instruction, sets the registers to new values and returns a program counter update.
	 *
	 * @param m Machine file that contains an Instruction of that type.
	 */
	@Override
	public int execute(Machine m) {
		int value1 = m.getRegisters().get(result);
		int value2 = m.getRegisters().get(source);
		m.getRegisters().set(result, value1 + value2);
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
		else if (otherObject instanceof AddInstruction other)
			return
					this.source.equals(other.source) && this.result.equals(other.result) && this.opcode.equals(other.opcode) && this.label.equals(other.label);
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(OP_CODE, result, source);
	}
}
