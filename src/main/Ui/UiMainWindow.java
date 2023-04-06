package main.Ui;

import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;

public class UiMainWindow extends Ui {
    
    public UiMainWindow(Game gp) {
        super(gp);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();// CAPTURA O BOTÃO PRESSIONADO

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            if (option>0){
                option--;
            }else{
                option=menu.length-1;
            }
            gp.playEffect(0);
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            if (option==menu.length-1){
                option=0;
            }else{
                option++;
            }
            gp.playEffect(0);
        }
        if(code==KeyEvent.VK_ENTER){
            if (option==0){
                gp.gameState = gp.playState;
                gp.uiManager.setTelaAtiva(1);
                gp.setLevel(1);   
                                           
            }
            if (option==1){
                System.out.println("OPCOES");
            }
            if (option==2){
                gp.setVisible(false);
                System.exit(0);
            }
            gp.playEffect(0);
        }
    }

    @Override
    public void sound(){
        //gp.playMusic(1);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(gameFont);
        g2.setColor(Color.white);

        int y;
        int x;
        //TITULO
        String text = "JOGUINHO SEM NOME";
        y = 150;
        setFontSize(60, Color.gray, g2);
        drawString(text, getCentroTelaText(text, g2), y, g2);
        setFontSize(60, Color.white, g2);
        drawString(text, getCentroTelaText(text, g2)-5, y, g2);

        //RECORD
        String strRecord = "Record: " + gp.record;
        y = 300;
        setFontSize(60, Color.gray, g2);
        drawString(strRecord, getCentroTelaText(strRecord, g2), y, g2);
        setFontSize(60, Color.white, g2);
        drawString(strRecord, getCentroTelaText(strRecord, g2)-5, y, g2);

        //MENU
        menu[0] = "NOVO JOGO";
        menu[1] = "OPCOES";
        menu[2] = "SAIR";

        y = gp.screenHeight - 40;
        setFontSize(25, Color.white, g2);
        for(int i=menu.length-1; i>=0; i--){
            x = getCentroTelaText(menu[i], g2);
            drawString(menu[i], x, y, g2);
            if (option == i){
                drawString(">", x-20, y, g2);
            }
            y-=40;
        }
    }
}
