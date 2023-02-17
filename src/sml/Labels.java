package sml;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static sml.Instruction.NORMAL_PROGRAM_COUNTER_UPDATE;

// TODO: write a JavaDoc for the class

/**
 *
 * @author ...
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
			throw new IllegalArgumentException("Your input contains duplicate labels");
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
	public int getAddress(String label) {
		// TODO: Where can NullPointerException be thrown here?
		//       (Write an explanation.)
		//       Add code to deal with non-existent labels.
		// variable "labels" is a hash map with key/value pairs of a label (String) and it's address (int).
		// The address shows the position of the instruction with the label in the SML program (to set the program counter accordingly)
		// In case you try to get the value of a HashMap key with the .get(key) method and this key does not exist in the map the return value is "null".
		// NullPointerException occurs if the code tries to use this null value for further steps (in this case an address (integer) should be returned).
		// In case of this specific program, "null" can't be converted to int and the program fails with NullPointerException.
		if (!labels.containsKey(label)) {
			return NORMAL_PROGRAM_COUNTER_UPDATE;
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
