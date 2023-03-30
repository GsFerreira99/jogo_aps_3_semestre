package main.Ui;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UiGame extends Ui{

    BufferedImage tempo, skillSpeed;
    int temporizador = 0;
    

    public UiGame(Game gp) {
        super(gp);
        definirImagens();
    }

    @Override
    public void sound() {
        gp.playMusic(2);
    }

    public void definirImagens(){
        try{
            tempo = ImageIO.read(new FileInputStream("res/player/Tempo.png"));
            skillSpeed = ImageIO.read(new FileInputStream("res/interface/skill_speed.png"));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();// CAPTURA O BOTÃO PRESSIONADO
        // ESTRUTURA DE CONTROLE PARA DEFINIR QUAL BOTÃO FOI PRESSIONADO
        if(code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_ENTER) {
            if (gp.gameState == gp.gameOver){
                gp.uiManager.setTelaAtiva(0);
                gp.gameState = gp.menuState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();// CAPTURA QUAL BOTÃO FOI SOLTO

        // ESTRUTURA DE CONTROLE PARA DEFINIR QUAL BOTÃO FOI SOLTO
        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }

    public void draw(Graphics2D g2) {
        String text = "SCORE: "+ gp.player.score;
        int x = (gp.maxScreenCol-3)*gp.tileSize;
        g2.setFont(gameFont);
        g2.setColor(Color.white);
        setFontSize(20, Color.white, g2);
        drawString(text, x, gp.tileSize-(gp.tileSize/4), g2);
        tempo(g2);
        // skills(g2);
    }

    public void tempo(Graphics2D g2) {
        int count = 0;
        for(int i=0;i<gp.player.vida;i++){
            g2.drawImage(tempo, gp.tileSize+count, gp.tileSize/4, gp.tileSize-25, gp.tileSize-25, null);
            count+=30;
        }
        temporizador ++;
        if (temporizador == 500){
            gp.player.vida -= 1;
            temporizador = 0;
        }
        if  (gp.player.vida == 0){
            gp.gameState = gp.gameOver;
        }
    }

    public void gameOver(Graphics2D g2){
        setFontSize(60, Color.white, g2);
        drawString("GAME OVER", getCentroTelaText("GAME OVER", g2), (gp.maxScreenRow/2)* gp.tileSize, g2);
        setFontSize(60, Color.red, g2);
        drawString("GAME OVER", getCentroTelaText("GAME OVER", g2)-5, (gp.maxScreenRow/2)* gp.tileSize, g2);
        for (int t = 0; t < 200; t++) { gp.levelManager.getActiveLevel().items[t] = null;}
        if (gp.player.score > gp.record){
            gp.record = gp.player.score;
        }
    }

    // public void skills(Graphics2D g2){
    //     drawString("SKILLS: ", gp.tileSize*9, gp.screenHeight-(gp.tileSize*3), g2);
    //     g2.drawImage(skillSpeed, gp.tileSize*9+10, gp.screenHeight-(gp.tileSize*3)+20, gp.tileSize, gp.tileSize, null);
    //     g2.drawImage(skillSpeed, gp.tileSize*10+10, gp.screenHeight-(gp.tileSize*3)+20, gp.tileSize, gp.tileSize, null);
    //     g2.drawImage(skillSpeed, gp.tileSize*11+10, gp.screenHeight-(gp.tileSize*3)+20, gp.tileSize, gp.tileSize, null);
    //     g2.drawImage(skillSpeed, gp.tileSize*12+10, gp.screenHeight-(gp.tileSize*3)+20, gp.tileSize, gp.tileSize, null);
    // }
}
