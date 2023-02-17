package sml;

// TODO: write a JavaDoc for the class

import java.io.IOException;

/**
 * Represents an abstract instruction.
 *
 * @author ...
 */
public abstract class Instruction {
	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name - gets checked if it matches one of the seven possible opcodes (case-sensitive)
	 */
	public Instruction(String label, String opcode) {
		this.label = label;

		if (opcode.matches("add|sub|mul|div|out|mov|jnz")) {
			this.opcode = opcode;
		}
		else { throw new IllegalArgumentException("Not a valid opcode");}
	}

	public String getLabel() {
		return label;
	}

	public String getOpcode() {
		return opcode;
	}

	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */

	public abstract int execute(Machine machine);

	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	// TODO: What does abstract in the declaration below mean?
	//       (Write a short explanation.)
	//
	//		 Abstract classes have no body, just a method signature. This class ("Instruction") is declared abstract with 3 abstract methods.
	//       An abstract class cannot be instantiated, however, as in this program, subclasses can extend it.
	//		 The Subclasses of "Instruction" are not declared abstract. However, as they are extending an abstract class, they have to implement
	//		 all abstract methods of the parent class.

	@Override
	public abstract String toString();

	// TODO: Make sure that subclasses also implement equals and hashCode (needed in class Machine).

	protected abstract boolean equals(Instruction ins);
	public abstract int hashCode();


}
