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
        loadMap("res/maps/fase_1.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile(ImageIO.read(new FileInputStream("res/tiles/rua_v.png")), false);
            tile[1] = new Tile(ImageIO.read(new FileInputStream("res/tiles/rua_h.png")), false);
            tile[2] = new Tile(ImageIO.read(new FileInputStream("res/tiles/grama.png")), true);
            tile[3] = new Tile(ImageIO.read(new FileInputStream("res/tiles/rua_bot_left.png")), false);
            tile[4] = new Tile(ImageIO.read(new FileInputStream("res/tiles/rua_bot_mid.png")), false);
            tile[5] = new Tile(ImageIO.read(new FileInputStream("res/tiles/rua_bot_right.png")), false);
            tile[6] = new Tile(ImageIO.read(new FileInputStream("res/tiles/rua_center.png")), false);
            tile[7] = new Tile(ImageIO.read(new FileInputStream("res/tiles/rua_left_mid.png")), false);
            tile[8] = new Tile(ImageIO.read(new FileInputStream("res/tiles/rua_right_mid.png")), false);
            tile[9] = new Tile(ImageIO.read(new FileInputStream("res/tiles/rua_top_left.png")), false);
            tile[10] = new Tile(ImageIO.read(new FileInputStream("res/tiles/rua_top_mid.png")), false);
            tile[11] = new Tile(ImageIO.read(new FileInputStream("res/tiles/rua_top_right.png")), false);
            tile[12] = new Tile(ImageIO.read(new FileInputStream("res/tiles/space.png")), true);
        } catch (IOException e){e.printStackTrace();}
    }

    public void loadMap(String mapFile) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(mapFile));
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
