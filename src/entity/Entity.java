package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int largura, altura;
    public int speed;
    public BufferedImage image;

<<<<<<< HEAD
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
=======
    public BufferedImage[] up = new BufferedImage[10];
    public BufferedImage[] down = new BufferedImage[10];
    public BufferedImage[] left = new BufferedImage[10];
    public BufferedImage[] right = new BufferedImage[10];
>>>>>>> 2dfd2c9b4936183423ea13574086b44ec8d961bb
    public String direction;
    public int spriteCounter=0;
    public int spriteNum=1;
    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;
}
