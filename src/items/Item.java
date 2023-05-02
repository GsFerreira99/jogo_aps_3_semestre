package items;

import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
    public BufferedImage image;
    public String name;
    public boolean colision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48/2,48/2);
    public  int solidAreaDefaultX = 0;
    public  int solidAreaDefaultY = 0;
    public Boolean superItem = false;
    public int timeEffect;
    public int countTimeEffect;

    public int score = 0;

    public boolean primeiraExibicao = true;
    public void effect(Game gp){
        gp.player.score+= this.score;
        gp.playEffect(4, -15f);
        gp.levelManager.getActiveLevel().contadorLixos--;
        if (gp.player.score >= gp.player.recuperaTempo){
            gp.player.tempo++;
            gp.player.recuperaTempo += gp.player.levelup;
            if(gp.player.levelup <= 150){
                gp.player.levelup += 10;
        }
        }
    }
    public void deactiveEffect(Game gp){
        
    }
    public Item(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public void draw(Graphics2D g2, Game gp) {
        g2.drawImage(image, worldX, worldY, gp.tileSize-10, gp.tileSize-10, null);
    }

    public void setPrimeiraExibicao(){
        primeiraExibicao = false;
    }
}
