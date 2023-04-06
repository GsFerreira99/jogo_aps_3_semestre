package items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Guardanapo extends Item{

    public Guardanapo(int worldX, int worldY) {
        super(worldX, worldY);
        this.score = 60;
        name = "Guardanapo";
        try {
            image = ImageIO.read(new FileInputStream("res/items/guardanapo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
