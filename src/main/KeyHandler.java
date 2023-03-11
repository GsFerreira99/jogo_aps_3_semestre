package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    // VARIAVEIS PARA CONTROLE DOS BOTÕES PRESSIONADOS
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        // CAPTURA O BOTÃO PRESSIONADO
        int code = e.getKeyCode();

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
        // CAPTURA QUAL BOTÃO FOI SOLTO
        int code = e.getKeyCode();

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
}
