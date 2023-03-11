package items;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperItem {
    public BufferedImage image;
    public String name;
    public boolean colision = false;
    public int worldX, worldY;

    public boolean primeiraExibicao = true;

    public SuperItem(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public void draw(Graphics2D g2, Game gp) {
        g2.drawImage(image, worldX, worldY, gp.tileSize/2, gp.tileSize/2, null);
    }

    public void setPrimeiraExibicao(){
        primeiraExibicao = false;
    }
}
