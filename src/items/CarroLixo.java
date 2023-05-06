package items;
import javax.imageio.ImageIO;

import main.Game;

import java.awt.Graphics2D;
import java.io.FileInputStream;
import java.io.IOException;

public class CarroLixo extends Item{
    public CarroLixo(int worldX, int worldY) {
        super(worldX, worldY);
        superItem = true;
        countTimeEffect = 0;
        name = "Carro do Lixo";
        try {
            image = ImageIO.read(new FileInputStream("res/items/caminhao/caminhao_left_1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void getCaminhaoImage(Game gp) {
        try{
            gp.player.up[0] = ImageIO.read(new FileInputStream("res/items/caminhao/caminhao_up.png"));
            gp.player.up[1] = ImageIO.read(new FileInputStream("res/items/caminhao/caminhao_up.png"));
            gp.player.down[0] = ImageIO.read(new FileInputStream("res/items/caminhao/caminhao_down.png"));
            gp.player.down[1] = ImageIO.read(new FileInputStream("res/items/caminhao/caminhao_down.png"));
            gp.player.left[0] = ImageIO.read(new FileInputStream("res/items/caminhao/caminhao_left_1.png"));
            gp.player.left[1] = ImageIO.read(new FileInputStream("res/items/caminhao/caminhao_left_2.png"));
            gp.player.left[2] = ImageIO.read(new FileInputStream("res/items/caminhao/caminhao_left_3.png"));
            gp.player.right[0] = ImageIO.read(new FileInputStream("res/items/caminhao/caminhao_right_1.png"));
            gp.player.right[1] = ImageIO.read(new FileInputStream("res/items/caminhao/caminhao_right_2.png"));
            gp.player.right[2] = ImageIO.read(new FileInputStream("res/items/caminhao/caminhao_right_2.png"));
            gp.player.altura = gp.tileSize;
            gp.player.largura = gp.tileSize;
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void effect(Game gp){
        if (gp.player.activeItem == null){
            getCaminhaoImage(gp);
            gp.player.activeItem = this;
            gp.player.som = 6;
            gp.player.somVolume = -20f;
            gp.playEffect(5, -10f);
            gp.player.speed = gp.player.speed*2;
            this.timeEffect = 10;
        }else if (gp.player.activeItem.name == name) {
            gp.player.activeItem.timeEffect = 10;
        }
    }
    public void deactiveEffect(Game gp){
        gp.player.getPlayerImage();
        gp.player.speed = 2;
        gp.player.som = 3;
        gp.player.somVolume = -35f;
        gp.player.activeItem = null;
    }

    public void draw(Graphics2D g2, Game gp) {
        g2.drawImage(image, worldX, worldY, gp.tileSize-2, gp.tileSize-2, null);
    }

}
