package app.cleancode;

import java.util.List;

public class DrawingInstruction {
    public final InstructionType instruction;
    public final List<Float> operands;

    public DrawingInstruction(InstructionType instruction, List<Float> operands) {
        this.instruction = instruction;
        this.operands = List.copyOf(operands);
    }
}
