package app.cleancode;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import javax.imageio.ImageIO;

public class Pixelater {
    public static void main(String[] args) throws Exception {
        String input = Files.readString(Paths.get("config.pxl"));
        List<DrawingInstruction> instructions = new FileParser().parse(input);
        BufferedImage image = null;
        Graphics graphics = null;
        for (DrawingInstruction instruction : instructions) {
            switch (instruction.instruction) {
                case CREATE: {
                    image = new BufferedImage(instruction.operands.get(0).intValue(),
                            instruction.operands.get(1).intValue(), BufferedImage.TYPE_4BYTE_ABGR);
                    graphics = image.getGraphics();
                    break;
                }
                case COLOR: {
                    graphics.setColor(new Color(instruction.operands.get(0),
                            instruction.operands.get(1), instruction.operands.get(2)));
                    break;
                }
                case CIRCLE: {
                    int radius = instruction.operands.get(2).intValue();
                    int centerX = instruction.operands.get(0).intValue() - radius;
                    int centerY = instruction.operands.get(1).intValue() - radius;
                    graphics.fillOval(centerX, centerY, radius * 2, radius * 2);
                    break;
                }
                case LINE: {
                    graphics.drawLine(instruction.operands.get(0).intValue(),
                            instruction.operands.get(1).intValue(),
                            instruction.operands.get(2).intValue(),
                            instruction.operands.get(3).intValue());
                    break;
                }
                case RECT: {
                    graphics.fillRect(instruction.operands.get(0).intValue(),
                            instruction.operands.get(1).intValue(),
                            instruction.operands.get(2).intValue(),
                            instruction.operands.get(3).intValue());
                    break;
                }
            }
        }
        graphics.dispose();
        File outFile = new File("out.png");
        ImageIO.write(image, "png", outFile);
        Desktop.getDesktop().open(outFile);
    }
}
