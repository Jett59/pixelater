package app.cleancode;

import java.util.ArrayList;
import java.util.List;

public class FileParser {
    private DrawingInstruction parseInstruction(String line) {
        String[] words = line.trim().split(" ");
        if (words.length < 1) {
            throw new IllegalArgumentException("Empty line");
        }
        InstructionType type = InstructionType.valueOf(words[0].toUpperCase());
        List<Float> operands = new ArrayList();
        for (int i = 1; i < words.length; i++) {
            operands.add(Float.parseFloat(words[i]));
        }
        return new DrawingInstruction(type, operands);
    }

    public List<DrawingInstruction> parse(String input) {
        String[] lines = input.split("\n");
        List<DrawingInstruction> instructions = new ArrayList<>();
        for (String line : lines) {
            instructions.add(parseInstruction(line));
        }
        return instructions;
    }
}
