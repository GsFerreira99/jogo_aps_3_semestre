package items;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperItem {
    public BufferedImage image;
    public String name;
    public boolean colision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public  int solidAreaDefaultX = 0;
    public  int solidAreaDefaultY = 0;

    public int score = 0;

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
