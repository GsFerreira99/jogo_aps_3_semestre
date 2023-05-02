package entity;
import main.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Vilao extends Entity {

    Game gp;
   
    public Vilao(Game gp, int x, int y) {
        this.gp = gp;

        solidArea = new Rectangle();
        solidArea.x = 4;
        solidArea.y = 7;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 20;
        defautX = x;
        defautY = y;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        this.x=defautX;
        this.y= defautY;
        locx=x;
        locy= y;
        largura = gp.tileSize-4;
        altura = gp.tileSize-4;
        speed=1;
        direction = "padrão";
        
    }

    public void getPlayerImage() {
        try{
            up1 = ImageIO.read(new FileInputStream("res/player/boy_up_1.png"));
            up2 = ImageIO.read(new FileInputStream("res/player/boy_up_2.png"));
            down1 = ImageIO.read(new FileInputStream("res/player/boy_down_1.png"));
            down2 = ImageIO.read(new FileInputStream("res/player/boy_down_2.png"));
            left1 = ImageIO.read(new FileInputStream("res/player/boy_left_1.png"));
            left2 = ImageIO.read(new FileInputStream("res/player/boy_left_2.png"));
            right1 = ImageIO.read(new FileInputStream("res/player/boy_right_1.png"));
            right2 = ImageIO.read(new FileInputStream("res/player/boy_right_2.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
   
    public ArrayList<Integer> findPath(int startX, int startY, int targetX, int targetY) {
        int startCol = startX/gp.tileSize;
        int startRow = startY/gp.tileSize;
        int targetCol = targetX/gp.tileSize;
        int targetRow = targetY/gp.tileSize;
        int numVertices = gp.maxScreenCol*gp.maxScreenRow;
        // Encontra o índice do vértice inicial e final
        int startIndex = startCol * gp.maxScreenRow + startRow;
        int targetIndex = targetCol * gp.maxScreenRow + targetRow;
    
        // Vetor de visitados e fila para BFS
        boolean[] visitado = new boolean[numVertices];
        int[] fila = new int[numVertices];
        int inicioFila = 0;
        int fimFila = 0;
    
        // Marca o vértice inicial como visitado e adiciona à fila
        visitado[startIndex] = true;
        fila[fimFila++] = startIndex;
    
        // Vetor de pais para reconstruir o caminho depois
        int[] pai = new int[numVertices];
        Arrays.fill(pai, -1);
    
        // BFS
        while (inicioFila != fimFila) {
            int verticeAtual = fila[inicioFila++];
            for (int vizinho = 0; vizinho < gp.tileM.graph[verticeAtual].length; vizinho++) {
                if (gp.tileM.graph[verticeAtual][vizinho]){
                    if (!visitado[vizinho]) {
                        visitado[vizinho] = true;
                        fila[fimFila++] = vizinho;
                        pai[vizinho] = verticeAtual;
                        
                        if (vizinho == targetIndex) {
                            // Chegou ao destino, reconstrói o caminho e retorna
                            ArrayList<Integer> caminho = new ArrayList<>();
                            int paiAtual = pai[vizinho];
                            while (paiAtual != -1) {
                                caminho.add(paiAtual);
                                paiAtual = pai[paiAtual];
                            }
                            
                            Collections.reverse(caminho);
                            if (caminho.get(0)==startIndex){
                                caminho.remove(0);
                            }
                            caminho.add(targetIndex); 
                            return caminho;
                        }
                    }
                }
            }
            }
        
        // Não encontrou caminho
        return null;
    }
   
    public void update(Player player){
        
        ArrayList<Integer> caminho = findPath(locx, locy, player.locx, player.locy);
        System.out.println(caminho);
        if (caminho == null || caminho.isEmpty()){
            gp.playEffect(7, -10f);
            try {
                Thread.sleep(3000); // 1000 milissegundos = 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gp.gameState = gp.gameOver;
            return;
        }
        int fimTile = caminho.get(0);
        
        int fimCol = fimTile/gp.maxScreenRow;
        int fimRow = fimTile%gp.maxScreenRow;
        int fimx = fimCol*gp.tileSize;
        int fimy = fimRow*gp.tileSize;
        
        if(y > fimy) {
            direction = "up";
            
        }else if(y < fimy) {
            direction = "down";
           
        }else if(x > fimx) {
            direction = "left";
        }else if(x < fimx) {
            direction = "right";
            
        }
        
        if(!gp.cCHecker.checkTile(this, direction)) {
            switch (direction) {
                case "up" -> y -= speed;
                case "down" -> y += speed;
                case "left" -> x -= speed;
                case "right" -> x += speed;
            }
        }
        
       
       
        spriteCounter++;
        if(spriteCounter > 10) {
            if (spriteNum == 1) {
                spriteNum = 2;
            } else if (spriteNum == 2) {
                spriteNum=1;
                
            }
            spriteCounter=0;
        }
        locx=x;
        locy= y;
        if (direction == "left"){
            locx+=27;
        }
        if (direction == "up"){
            locy+=25;
        }
        
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
            }
            case "padrão" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
            }

        }

        g2.drawImage(image, x, y, largura, altura, null);
    }
}
