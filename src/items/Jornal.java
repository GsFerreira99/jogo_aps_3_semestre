package items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Jornal extends SuperItem{
    public Jornal(int worldX, int worldY) {
        super(worldX, worldY);
        this.score = 80;
        name = "Jornal";
        try {
            image = ImageIO.read(new FileInputStream("res/items/jornal.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
