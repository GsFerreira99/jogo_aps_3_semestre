package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {

    // Tamanho de um tile da tela
    final int originalTileSize = 16;
    // Escala de multiplicação do tile
    final int scale = 3;

    // tamanho final de um tile
    public int tileSize = originalTileSize * scale;
    //Quantidade de colunas da tela
    public final int maxScreenCol = 16;
    //quantidade de linhas da tela
    public final int maxScreenRow = 12;
    // Largura total da tela
    final int screenWidth = tileSize * maxScreenCol;
    // Altura total da tela
    final int screenHeight = tileSize * maxScreenRow;

    // Classe responsável pela leitura de teclas apertadas
    KeyHandler keyH = new KeyHandler();

    Thread gameThread;
    // Classe responsável pelo gerenciamento dos tiles desenhados na tela
    TileManager tileM = new TileManager(this);
    // Classe responsável pelas funcionalidades do player principal
    Player player = new Player(this, keyH);
    LevelManager levelManager = new LevelManager(this);

    // ESTADOS DO JOGO
    public int gameState = 1;
    public final int mainMenuState = 0;
    public final int playState = 1;
    public final int pauseState = 2;

    JFrame window;
    // Classe responsável pela checagem das colisões
    public ColisionChecker cCHecker = new ColisionChecker(this);

    //FPS
    int FPS = 60;

    public Game(JFrame window) {
        this.window = window;
        // DEFINE O TAMANHO DA TELA
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        // DEFINE A COR DE FUNDO DA TELA
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        // ADICIONA A CLASSE DE CONTROLE DAS TECLAS PRESSIONADAS
        this.addKeyListener(keyH);
        this.setFocusable(true);

        //DEFINE A FASE
        setLevel(2);
    }

    public void setLevel(int level){
        levelManager.setLevel(level);
        tileM.loadMap(levelManager.getLevelMap());
    }

    public void startHameThread() {
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

        // DESENHA O MAPA
        tileM.draw(g2);
        // DESENHA O JOGADOR
        player.draw(g2);
        g2.dispose();
    }
}
