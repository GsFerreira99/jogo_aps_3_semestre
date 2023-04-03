package tile;

import java.awt.image.BufferedImage;

public class Tile {

    public BufferedImage image;
    public boolean colision = false;

    public Tile(BufferedImage image, Boolean colision){
        this.image = image;
        this.colision = colision;
    }
}
