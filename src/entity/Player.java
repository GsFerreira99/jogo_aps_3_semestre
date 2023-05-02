package entity;
import main.Game;
import main.KeyHandler;
import main.Ui.Ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import items.Item;


public class Player extends Entity {

    Game gp;
    KeyHandler keyH;
    public int score;
    public int tempo;
    public int recuperaTempo;
    public int levelup;
    public Item activeItem;
    public int som = 3;
    public float somVolume = -35f;

    public Player(Game gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        solidArea = new Rectangle();
        solidArea.x = 4;
        solidArea.y = 7;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 20;
        getPlayerImage();
    }

    public void setDefaultValues() {
        x=(gp.tileSize*1)+2;
        y= (gp.tileSize*4)+2;
        locx = x+15;
        locy = y+15;
        largura = gp.tileSize-7;
        altura = gp.tileSize-7;
        speed=2;
        direction = "down";
        tempo = 5;
        score = 0;
        recuperaTempo = 100;
        levelup = 50;
        activeItem = null;
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
            dead = ImageIO.read(new FileInputStream("res/player/PlayerDead.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        Ui keyH = gp.uiManager.getTelaAtiva();

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if(keyH.upPressed) {
                direction = "up";
            }else if(keyH.downPressed) {
                direction = "down";
            }else if(keyH.leftPressed) {
                direction = "left";
            }else if(keyH.rightPressed) {
                direction = "right";
            }

            // CHECAGEM DE COLISÃO
            

            //CHECAGEM DE COLISÃO COM OBJETOS
            int objIndex = gp.cCHecker.checkObject(this, 
            true);

            pegarLixo(objIndex);

            // SE COLISÃO FOR 'false' SE MOVER
            if(!gp.cCHecker.checkTile(this, direction)) {
                switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
                }
            }

            spriteCounter++;
            if(spriteCounter > 10) {
                if (spriteNum == 1) {
                    gp.playEffect(som, somVolume);
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum=1;
                    
                }
                spriteCounter=0;
            }

        }
        if (activeItem != null){
           activeItem.countTimeEffect++;
           if (activeItem.countTimeEffect == 60){
                activeItem.timeEffect --;
                activeItem.countTimeEffect = 0;
                if (activeItem.timeEffect == 0){
                    activeItem.deactiveEffect(gp);
                }
           }
        }
        locx = x+15;
        locy = y+15;
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

        }

        g2.drawImage(image, x, y, largura, altura, null);
    }

    public void pegarLixo(int i){
        if(i!=999){
            Item item = gp.levelManager.getActiveLevel().items[i];
            item.effect(gp);
            gp.levelManager.getActiveLevel().items[i] = null;
            
        }
    }
}
