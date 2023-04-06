package items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Caderno extends Item{
    public Caderno(int worldX, int worldY) {
        super(worldX, worldY);
        this.score = 40;
        name = "Caderno";
        try {
            image = ImageIO.read(new FileInputStream("res/items/caderno.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
