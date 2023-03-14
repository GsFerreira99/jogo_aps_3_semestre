package main.Ui;

import main.Game;

import java.awt.*;

public class UiManager {

    Game gp;
    Ui []telas = new Ui[2];

    int telaAtiva = 0;
    public UiManager(Game gp){
        this.gp = gp;
        definirTelas();
    }

    public Ui getTelaAtiva(){
        return telas[telaAtiva];
    }

    public void setTelaAtiva(int i){
        this.telaAtiva = i;
        telas[i].sound();
    }

    public void definirTelas(){
        telas[0] = new UiMainWindow(gp);
        telas[1] = new UiGame(gp);
    }

    public void draw(Graphics2D g2){
        telas[telaAtiva].draw(g2);
    }

}
