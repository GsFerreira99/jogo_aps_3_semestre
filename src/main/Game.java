package main;

import entity.Player;
import main.Ui.Ui;
import main.Ui.UiManager;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {
    final int originalTileSize = 12;// Tamanho de um tile da tela
    final int scale = 3;// Escala de multiplicação do tile
    public int tileSize = originalTileSize * scale;// tamanho final de um tile
    public final int maxScreenCol = 21;//Quantidade de colunas da tela
    public final int maxScreenRow = 19;//quantidade de linhas da tela
    public final int screenWidth = tileSize * maxScreenCol;// Largura total da tela
    public final int screenHeight = tileSize * maxScreenRow;// Altura total da tela
    Thread gameThread;
    public TileManager tileM = new TileManager(this);// Classe responsável pelo gerenciamento dos tiles desenhados na tela

    public LevelManager levelManager = new LevelManager(this);// responsavel pelo gerenciamento dos niveis
    public UiManager uiManager = new UiManager(this);// responsavel pelo gerenciamentos das telas
    public Som som = new Som();// responsavel pelo som
    KeyHandler keyH = new KeyHandler(this);// Classe responsável pela leitura de teclas apertadas
    public Player player = new Player(this, keyH);// Classe responsável pelas funcionalidades do player principal
    // ESTADOS DO JOGO
    public final int menuState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOver = 3;
    public int gameState = menuState;
    JFrame window;
    public ColisionChecker cCHecker = new ColisionChecker(this);// Classe responsável pela checagem das colisões
    int FPS = 60;//FPS
    public Game(JFrame window) {
        this.window = window;
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));// DEFINE O TAMANHO DA TELA
        this.setBackground(Color.black);// DEFINE A COR DE FUNDO DA TELA
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);// ADICIONA A CLASSE DE CONTROLE DAS TECLAS PRESSIONADAS
        this.setFocusable(true);
        setupGame();//DEFINE AS CONFIGURAÇÕES INICIAIS
    }

    public void setupGame(){
        gameState = menuState;
        uiManager.setTelaAtiva(0);
    }
    public void setLevel(int level){
        levelManager.setLevel(level);
        tileM.loadMap(levelManager.getLevelMap());
        levelManager.getActiveLevel().start();
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        // CALCULO DA VELOCIDADE DE EXIBIÇÃO DAS AÇOES DOS OBJETOS DO JOGO
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta +=(currentTime-lastTime)/drawInterval;
            lastTime = currentTime;
            if(delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        // RESPONSÁVEL PELA MOVIMENTAÇÃO DO JOGADOR
        if (gameState == playState) {
            player.update();
        }
    }

    public void paintComponent(Graphics g) {
        // DESENHA NA TELA TODOS OS OBJETOS DO JOGO
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if (gameState == menuState) {
            //UI MENU
        }
        Level levelAtual = levelManager.getActiveLevel();
        if (gameState == playState) {
            tileM.draw(g2);// DESENHA O MAPA
            levelManager.getActiveLevel().draw(g2, this);//LEVEL
            player.draw(g2);// DESENHA O JOGADOR
            if(levelAtual.contadorLixos == 20){
                gameState = gameOver;

            }
        }
        if(gameState == gameOver){
            tileM.draw(g2);// DESENHA O MAPA
            uiManager.getTelaAtiva().gameOver(g2);
        }
        uiManager.draw(g2);
        g2.dispose();
    }

    public void playMusic(int i) {
        som.setSom(i);
        som.setVolume(-10f);
        som.play();
        som.loop();
    }

    public void stopMusic(){
        som.stop();
    }

    public void playEffect(int i) {
        som.setSom(i);
        som.play();
    }
}
