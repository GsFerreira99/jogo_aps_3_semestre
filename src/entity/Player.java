package entity;
import main.Game;
import main.KeyHandler;
import main.Ui.Ui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Player extends Entity {

    Game gp;
    KeyHandler keyH;
    public int score=0;
    public int tempo = 5;
    public int recuperaTempo = 100;
    public int levelup = 50;

    public Player(Game gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

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
        speed=2;
        direction = "down";
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
            colisionOn = false;
            gp.cCHecker.checkTile(this);

            //CHECAGEM DE COLISÃO COM OBJETOS
            int objIndex = gp.cCHecker.checkObject(this, true);

            pegarLixo(objIndex);

            // SE COLISÃO FOR 'false' SE MOVER
            if(!colisionOn) {
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

        }

        g2.drawImage(image, x, y, gp.tileSize-10, gp.tileSize-10, null);
    }

    public void pegarLixo(int i){
        if(i!=999){
            score+=gp.levelManager.getActiveLevel().items[i].score;
            gp.levelManager.getActiveLevel().items[i] = null;
            gp.playEffect(4);
            gp.levelManager.getActiveLevel().contadorLixos--;
            if (score >= recuperaTempo){
                tempo++;
                recuperaTempo += levelup;
                if(levelup <= 300){
                levelup += 25;
            }
            }
        }
    }
}
