package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int defautX, defautY;
    public int locx, locy;
    public int largura, altura;
    public int speed;
    public BufferedImage image;

    public BufferedImage[] up = new BufferedImage[10];
    public BufferedImage[] down = new BufferedImage[10];
    public BufferedImage[] left = new BufferedImage[10];
    public BufferedImage[] right = new BufferedImage[10];
    public String direction;
    public int spriteCounter=0;
    public int spriteNum=0;
    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;
}
