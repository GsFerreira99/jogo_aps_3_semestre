package main;

import items.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Level {
    int numLevel;
    String mapFile;
    Game gp;
    

    public SuperItem[] items = new SuperItem[200];

    public String[] itemGenerator = new String[10];

    public int contadorLixos = 0;
    Timer timer;
    public Level(Game gp, int numLevel, String mapFile){
        this.gp = gp;
        this.numLevel = numLevel;
        this.mapFile = mapFile;

        itemGenerator[0] = "Caderno";
        itemGenerator[1] = "Guardanapo";
        itemGenerator[2] = "Jornal";
        itemGenerator[3] = "Revista";
        itemGenerator[4] = "Papel Higiênico";
    }

    public void start(){
        gp.player.tempo = 5;
        gp.player.score = 0;
        gp.player.recuperaTempo = 100;
        gp.player.levelup = 50;
        timer = new Timer();
        criacaoDeLixos();
        gp.stopMusic();
    }

    public void finish(){
        for (int t = 0; t < items.length; t++) {items[t] = null;}
        contadorLixos = 0;
        timer.cancel();

    }

    public void novoItem() {
        items[0] = new PapelHigienico(100,100);
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
                    int num = random.nextInt(0,4);
                    
                    switch (itemGenerator[num]){
                        case "Caderno":
                            items[counter] = new Caderno((x* gp.tileSize)+ gp.tileSize/4, (y*gp.tileSize)+ gp.tileSize/4);
                            break;
                        case "Guardanapo":
                            items[counter] = new Guardanapo((x* gp.tileSize)+ gp.tileSize/4, (y*gp.tileSize)+ gp.tileSize/4);
                            break;
                        case "Jornal":
                            items[counter] = new Jornal((x* gp.tileSize)+ gp.tileSize/4, (y*gp.tileSize)+ gp.tileSize/4);
                            break;
                        case "Revista":
                            items[counter] = new Revista((x* gp.tileSize)+ gp.tileSize/4, (y*gp.tileSize)+ gp.tileSize/4);
                            break;
                        case "Papel Higiênico":
                            items[counter] = new PapelHigienico((x* gp.tileSize)+ gp.tileSize/4, (y*gp.tileSize)+ gp.tileSize/4);
                            break;
                    }
                    System.out.println(items[counter]);
                    counter++;
                    contadorLixos++;
                }
            }
        }, 2000, 2000);
    }

    public void draw(Graphics2D g2, Game gp) {
        drawItems(g2);
        gp.uiManager.getTelaAtiva().draw(g2);
    }

    public void drawItems(Graphics2D g2){
        for(SuperItem item: items) {
            if(item!=null){
                item.draw(g2, gp);
            }
        }
    }
}
