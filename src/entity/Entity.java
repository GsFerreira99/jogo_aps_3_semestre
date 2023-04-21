package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int x, y;
    public int largura, altura;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter=0;
    public int spriteNum=1;
    public Rectangle solidArea;
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean colisionOn = false;
}
