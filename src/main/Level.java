package main;

import items.LixoPapel;
import items.SuperItem;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Level {
    int numLevel;
    String mapFile;
    Game gp;
    Timer timer = new Timer();

    public SuperItem[] items = new SuperItem[20];

    public Level(Game gp, int numLevel, String mapFile){
        this.gp = gp;
        this.numLevel = numLevel;
        this.mapFile = mapFile;
        //novoItem();
        criacaoDeLixos();
    }

    public void novoItem() {
        items[0] = new LixoPapel(100,100);
    }

    public void criacaoDeLixos() {
        timer.scheduleAtFixedRate(new TimerTask() {
            int counter = 0;
            @Override
            public void run() {
                Random random = new Random();
                if (items[items.length-1] == null){
                    int x, y;
                    while(true){
                        x = random.nextInt(1, gp.maxScreenCol);
                        y = random.nextInt(1, gp.maxScreenRow);
                        int tile = gp.tileM.mapTileNum[x][y];
                        if(!gp.tileM.tile[tile].colision){
                            break;
                        }
                    }
                    items[counter] = new LixoPapel((x* gp.tileSize)+ gp.tileSize/4, (y*gp.tileSize)+ gp.tileSize/4);
                    counter++;
                }else{System.out.println(items.length);}

            }
        }, 1000, 4000);
    }

    public void draw(Graphics2D g2, Game gp) {
        try{
            for(SuperItem item: items) {
                item.draw(g2, gp);
            }
        }catch (NullPointerException ignored){}

    }
}
