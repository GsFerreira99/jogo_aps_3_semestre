package items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class PapelHigienico extends SuperItem{
    public PapelHigienico(int worldX, int worldY) {
        super(worldX, worldY);
        this.score = 20;
        name = "Papel Higiênico";
        try {
            image = ImageIO.read(new FileInputStream("res/items/papelhigienico.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
