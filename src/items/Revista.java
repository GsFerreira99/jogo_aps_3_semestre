package items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Revista extends Item{
    public Revista(int worldX, int worldY) {
        super(worldX, worldY);
        this.score = 100;
        name = "Revista";
        try {
            image = ImageIO.read(new FileInputStream("res/items/revista.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
