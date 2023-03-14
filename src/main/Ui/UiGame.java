package main.Ui;

import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class UiGame extends Ui{

    public UiGame(Game gp) {
        super(gp);
    }

    @Override
    public void sound() {
        gp.playMusic(2);
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
        drawString(text, x, gp.tileSize/2, g2);
    }

    public void gameOver(Graphics2D g2){
        setFontSize(60, Color.white, g2);
        drawString("GAME OVER", getCentroTelaText("GAME OVER", g2), (gp.maxScreenRow/2)* gp.tileSize, g2);
        setFontSize(60, Color.red, g2);
        drawString("GAME OVER", getCentroTelaText("GAME OVER", g2)-5, (gp.maxScreenRow/2)* gp.tileSize, g2);
    }
}
