package main;

import javax.swing.JFrame;

public class Main {
    JFrame window;

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        // Define o titulo da janela do jogo
        window.setTitle("JOGUINHO");

        // Instancia a classe de controle do jogo
        Game gamePanel = new Game(window);
        // Adiciona o gamePanel a janela Principal
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // Inicia o loop principal do jogo
        gamePanel.startGameThread();
    }

}
