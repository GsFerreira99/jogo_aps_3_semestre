package tile;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    Game gp;
    public Tile[] tile;
    public int[][] mapTileNum;

    public TileManager(Game gp) {
        this.gp = gp;
        tile = new Tile[20];
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new FileInputStream("res/tiles/rua_v.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new FileInputStream("res/tiles/rua_h.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new FileInputStream("res/tiles/space.png"));
            tile[2].colision = true;
        }catch (IOException e){e.printStackTrace();}
    }

    public void loadMap() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("res/maps/fase_1.txt"));
            int col = 0;
            int row = 0;
            while (col<gp.maxScreenCol && row<gp.maxScreenRow){
                String line = br.readLine();
                while(col<gp.maxScreenCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxScreenCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x+= gp.tileSize;
            if(col == gp.maxScreenCol) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
