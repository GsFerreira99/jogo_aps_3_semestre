package main.Ui;

import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public abstract class Ui implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;// VARIAVEIS PARA CONTROLE DOS BOTÕES PRESSIONADOS
    Game gp;
    Font gameFont;
    Graphics2D g2;
    public String[] menu = new String[3];

    public int option = 0;

    public Ui(Game gp) {
        this.gp = gp;
        int size = 80;
        try{
            gameFont = Font.createFont(Font.TRUETYPE_FONT,
                            new File("res/fonts/ConnectionIi.otf"))
                    .deriveFont(Font.PLAIN, size);
        } catch (IOException | FontFormatException e) {
            gameFont = new Font("Arial", Font.PLAIN, size);
        }
    }

    public void setFontSize(int size, Color color, Graphics2D g2){
        g2.setFont(gameFont.deriveFont(Font.BOLD, size));
        g2.setColor(color);
    }

    public void drawString(String text, int x, int y, Graphics2D g2){
        g2.drawString(text, x, y);
    }

    public int getCentroTelaText(String text, Graphics2D g2){
        int lenght  = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - lenght/2;
    }

    public void draw(Graphics2D g2) {
    }

    public void sound() {
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void gameOver(Graphics2D g2) {
    }
}
