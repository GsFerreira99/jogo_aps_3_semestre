package main;

import items.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import main.Som;
import java.util.Timer;
import java.util.TimerTask;
import entity.Vilao;

public class Level {
    int numLevel;
    String mapFile;
    Game gp;
    Som som = new Som();

    public Item[] items = new Item[200];

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
        itemGenerator[5] = "Carro do Lixo";
    }
    public void playMusic(int i, float v) {
        som.setSom(i);
        som.setVolume(v);
        som.play();
        som.loop();
    }

    public void stopMusic(){
        som.stop();
    }

    public void playEffect(int i, float v) {
        som.setSom(i);
        som.play();
        som.setVolume(v);
    }
    
    public void start(){
        if(gp.player.activeItem != null){
            gp.player.activeItem.deactiveEffect(gp);
        }
        gp.player.setDefaultValues();
        gp.vilao1.setDefaultValues();
        gp.vilao2.setDefaultValues();
        timer = new Timer();
        criacaoDeLixos();
        gp.stopMusic();
        playEffect(2, -10f);
    }

    public void finish(){
        Arrays.fill(items, null);
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
                    int num = random.nextInt(6); // Escolha um número entre 0 e 6
                    // Ajusta a probabilidade de cair 6 para 1/10 (10%)
                    System.out.println(num);
                    if (num == 5) {
                        int newNum = random.nextInt(3);
                        if (newNum != 0) { // Probabilidade de 1 em 10
                            num = random.nextInt(3); // Escolha um número entre 0 e 5
                            System.out.println(num);
                        }
                        
                    }
                                
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
                        case "Carro do Lixo":
                            items[counter] = new CarroLixo((x* gp.tileSize)+ gp.tileSize/4, (y*gp.tileSize)+ gp.tileSize/4);
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
        for(Item item: items) {
            if(item!=null){
                item.draw(g2, gp);
            }
        }
    }
}
