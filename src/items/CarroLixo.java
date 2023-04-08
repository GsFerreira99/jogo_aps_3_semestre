package items;
import javax.imageio.ImageIO;

import main.Game;

import java.io.FileInputStream;
import java.io.IOException;

public class CarroLixo extends Item{
    public CarroLixo(int worldX, int worldY) {
        super(worldX, worldY);
        superItem = true;
        countTimeEffect = 0;
        name = "Carro do Lixo";
        try {
            image = ImageIO.read(new FileInputStream("res/items/boy_up_1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void effect(Game gp){
        if (gp.player.activeItem == null){
            gp.player.activeItem = this;
            gp.playEffect(4);
            gp.player.speed = gp.player.speed*2;
            this.timeEffect = 10;
        }else if (gp.player.activeItem.name == name) {
            gp.player.activeItem.timeEffect = 10;
        }
    }
    public void deactiveEffect(Game gp){
        gp.player.speed = 2;
        gp.player.activeItem = null;
    }
}
