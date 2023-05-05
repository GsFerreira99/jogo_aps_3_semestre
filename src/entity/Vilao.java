package entity;
import main.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Vilao extends Entity {

    Game gp;
   
    public Vilao(Game gp) {
        this.gp = gp;

        solidArea = new Rectangle();
        solidArea.x = 4;
        solidArea.y = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 20;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x=100;
        y= (gp.tileSize*2)-4;
        largura = gp.tileSize-4;
        altura = gp.tileSize-4;
        speed=1;
        direction = "padrão";
        
    }

    public void getPlayerImage() {
        try{
            up1 = ImageIO.read(new FileInputStream("res/player/boy_up_1.png"));
            up2 = ImageIO.read(new FileInputStream("res/player/boy_up_2.png"));
            down1 = ImageIO.read(new FileInputStream("res/player/boy_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res/player/boy_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("res/player/boy_left_1.png"));
            left2 = ImageIO.read(new FileInputStream("res/player/boy_left_2.png"));
            right1 = ImageIO.read(new FileInputStream("res/player/boy_right_1.png"));
            right2 = ImageIO.read(new FileInputStream("res/player/boy_right_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
   
    public void update(Player player){
        if (x != player.x || y != player.y) {
            
            if(y > player.y && !gp.cCHecker.checkTile(this, "up")) {
                direction = "up";
            }else if(y < player.y  && !gp.cCHecker.checkTile(this, "down")) {
                direction = "down";
            }else if(x > player.x  && !gp.cCHecker.checkTile(this, "left")) {
                direction = "left";
            }else if(x < player.x  && !gp.cCHecker.checkTile(this, "right")) {
                direction = "right";
            }
            
            switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
                }
            

            spriteCounter++;
            if(spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum=1;
                }
                spriteCounter=0;
            }

        }


    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
            case "padrão" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }

        }

        g2.drawImage(image, x, y, largura, altura, null);
    }
}
