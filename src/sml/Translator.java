package sml;

import sml.Exceptions.OpcodeNotFoundException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**

 * The translator of a <b>S</b><b>M<b>L</b> program.
 * Scans the provided document line by line for an SML program and separates labels and the program itself.
 * Calls the different instructions depending on the opcode of an instruction line in the provided file.
 *
 * @author Fabian Zischler
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
        String r = scan();
        String s = scan();

        ArrayList<String> input = new ArrayList<>();

        input.add(label);
        input.add(r);
        input.add(s);



        ReflectionInstructionFactory getInstruction = ReflectionInstructionFactory.getInstance();

       try {
           return getInstruction.createInstruction(opcode, input);
       } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException | OpcodeNotFoundException e) {
           throw new RuntimeException(e);
       }
    }


//           // TODO: add code for all other types of instructions
//
//           // TODO: Then, replace the switch by using the Reflection API
//
//           // TODO: Next, use dependency injection to allow this machine class
//               to work with different sets of opcodes (different CPUs)




    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}