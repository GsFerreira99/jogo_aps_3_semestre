package entity;
import main.Game;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class Vilao extends Entity {

    Game gp;
   
    public Vilao(Game gp) {
        this.gp = gp;

        solidArea = new Rectangle();
        solidArea.x = 4;
        solidArea.y = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 20;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
<<<<<<< HEAD
        x=100;
        y= (gp.tileSize*2)-4;
        largura = gp.tileSize-4;
        altura = gp.tileSize-4;
=======
        this.x=defautX;
        this.y= defautY;
        locx=x;
        locy= y;
        largura = gp.tileSize-12;
        altura = gp.tileSize-12;
>>>>>>> 2dfd2c9b4936183423ea13574086b44ec8d961bb
        speed=1;
        direction = "padrão";
        
    }

    public void getPlayerImage() {
        try{
            up[0] = ImageIO.read(new FileInputStream("res/vilao/vilao_up_1.png"));
            up[1] = ImageIO.read(new FileInputStream("res/vilao/vilao_up_2.png"));
            up[2] = ImageIO.read(new FileInputStream("res/vilao/vilao_up_3.png"));
            up[3] = ImageIO.read(new FileInputStream("res/vilao/vilao_up_4.png"));
            down[0] = ImageIO.read(new FileInputStream("res/vilao/vilao_down_1.png"));
            down[1] = ImageIO.read(new FileInputStream("res/vilao/vilao_down_2.png"));
            down[2] = ImageIO.read(new FileInputStream("res/vilao/vilao_down_3.png"));
            down[3] = ImageIO.read(new FileInputStream("res/vilao/vilao_down_4.png"));
            left[0] = ImageIO.read(new FileInputStream("res/vilao/vilao_left_1.png"));
            left[1] = ImageIO.read(new FileInputStream("res/vilao/vilao_left_2.png"));
            left[2] = ImageIO.read(new FileInputStream("res/vilao/vilao_left_3.png"));
            left[3] = ImageIO.read(new FileInputStream("res/vilao/vilao_left_4.png"));
            left[4] = ImageIO.read(new FileInputStream("res/vilao/vilao_left_5.png"));
            left[5] = ImageIO.read(new FileInputStream("res/vilao/vilao_left_6.png"));
            left[6] = ImageIO.read(new FileInputStream("res/vilao/vilao_left_7.png"));
            left[7] = ImageIO.read(new FileInputStream("res/vilao/vilao_left_8.png"));
            right[0] = ImageIO.read(new FileInputStream("res/vilao/vilao_right_1.png"));
            right[1] = ImageIO.read(new FileInputStream("res/vilao/vilao_right_2.png"));
            right[2] = ImageIO.read(new FileInputStream("res/vilao/vilao_right_3.png"));
            right[3] = ImageIO.read(new FileInputStream("res/vilao/vilao_right_4.png"));
            right[4] = ImageIO.read(new FileInputStream("res/vilao/vilao_right_5.png"));
            right[5] = ImageIO.read(new FileInputStream("res/vilao/vilao_right_6.png"));
            right[6] = ImageIO.read(new FileInputStream("res/vilao/vilao_right_7.png"));
            right[7] = ImageIO.read(new FileInputStream("res/vilao/vilao_right_8.png"));
           
    
        }catch (IOException e){
            e.printStackTrace();
        }
    }
   
<<<<<<< HEAD
    public void update(Player player){
        if (x != player.x || y != player.y) {
            
            if(y > player.y && !gp.cCHecker.checkTile(this, "up")) {
                direction = "up";
            }else if(y < player.y  && !gp.cCHecker.checkTile(this, "down")) {
                direction = "down";
            }else if(x > player.x  && !gp.cCHecker.checkTile(this, "left")) {
                direction = "left";
            }else if(x < player.x  && !gp.cCHecker.checkTile(this, "right")) {
                direction = "right";
            }
            
=======
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
    int sleep = 180;
    public void update(Player player){
        
        ArrayList<Integer> caminho = findPath(locx, locy, player.locx, player.locy);
        if (caminho == null || caminho.isEmpty()){
            gp.player.direction = "dead";
            if (sleep == 179){
                gp.playEffect(7, -5f);
            }
            if (sleep == 0){
                gp.gameState = gp.gameOver;
                sleep = 180;
            }else{sleep--;}
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
>>>>>>> 2dfd2c9b4936183423ea13574086b44ec8d961bb
            switch (direction) {
                    case "up" -> y -= speed;
                    case "down" -> y += speed;
                    case "left" -> x -= speed;
                    case "right" -> x += speed;
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

        }
<<<<<<< HEAD


=======
        
       
       
        spriteCounter++;
        if(spriteCounter > 2) {
            spriteNum++;
            if (spriteNum == up.length){
                spriteNum = 0;
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
        
>>>>>>> 2dfd2c9b4936183423ea13574086b44ec8d961bb
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if(up[spriteNum] == null){
                    spriteNum = 0;
                }
                image = up[spriteNum];
            }
            case "down" -> {
                if(down[spriteNum] == null){
                    spriteNum = 0;
                }
                image = down[spriteNum];
            }
            case "left" -> {
                if(left[spriteNum] == null){
                    spriteNum = 0;
                }
                image = left[spriteNum];
            }
            case "right" -> {
                if(right[spriteNum] == null){
                    spriteNum = 0;
                }
                image = right[spriteNum];
            }
            case "padrão" -> {
                if(down[spriteNum] == null){
                    spriteNum = 0;
                }
                image = down[spriteNum];
            }

        }

        g2.drawImage(image, x, y, largura, altura, null);
    }
}
