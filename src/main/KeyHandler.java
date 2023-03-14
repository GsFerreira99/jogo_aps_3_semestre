package main;

import main.Ui.Ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    Game gp;
    public KeyHandler(Game gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        gp.uiManager.getTelaAtiva().keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        gp.uiManager.getTelaAtiva().keyReleased(e);
    }
}
