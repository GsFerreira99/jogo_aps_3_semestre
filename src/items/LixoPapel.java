package items;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class LixoPapel extends SuperItem{
    public LixoPapel() {
        name = "Papel";
        try {
            image = ImageIO.read(new FileInputStream("res/items/papel.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}