package sml;

import sml.Exceptions.DuplicateLabelException;
import sml.Exceptions.LabelNotFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;

// TODO: write a JavaDoc for the class

/**
 *Organises the labels for the different instructions (especially important for Instructions that require a jump, like the JnzInstruction)
 *Labels are put into a map, with the label name (String) as key, and the address (number of line in the SML file where the label occurs first)
 *as the value. Labels have to be unique, because they are identifiers for the program counter. Trying to add duplicates will throw an exception
 *This class is used by the Translator class, which uses the addLabel method to add more labels to the Map.
 * @author Fabian Zischler
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);
		// TODO: Add a check that there are no label duplicates.
		if (Arrays.stream(labels.keySet().toArray())
						.anyMatch(label::equals)) {
			try {
				throw new DuplicateLabelException("Your input contains the following duplicate label: " + label);
			} catch (DuplicateLabelException e) {
				throw new RuntimeException(e);
			}
		} else {
			labels.put(label, address);
		}
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) throws LabelNotFoundException {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		//       Add code to deal with non-existent labels.
		// variable "labels" is a hash map with key/value pairs of a label (String) and it's address (int).
		// The address shows the position of the instruction with the label in the SML program (to set the program counter accordingly)
		// In case you try to get the value of a HashMap key with the .get(key) method and this key does not exist in the map the return value is "null".
		// NullPointerException occurs if the code tries to use this null value for further steps (in this case an address (integer) should be returned).
		// In case of this specific program, "null" can't be converted to int and the program fails with NullPointerException.
		if (!labels.containsKey(label)) {
			throw new LabelNotFoundException("The following label was not found: " + label);
		}
		else
		return labels.get(label);
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		// TODO: Implement the method using the Stream API (see also class Registers).
		if (!labels.isEmpty()) {

			return "List of Labels: " + "\n" + labels.entrySet()
					.stream()
					.sorted(Map.Entry.comparingByValue())
					.map(lbl -> "Label: " + lbl.getKey() + " - Address: " + lbl.getValue())
					.collect(Collectors.joining("\n"));
		}
		return "no labels stored";
	}

	// TODO: Implement equals and hashCode (needed in class Machine).


	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}



}
