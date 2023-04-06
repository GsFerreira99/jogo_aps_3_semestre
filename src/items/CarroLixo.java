package items;
import javax.imageio.ImageIO;

import main.Game;

import java.io.FileInputStream;
import java.io.IOException;

public class CarroLixo extends Item{
    public CarroLixo(int worldX, int worldY) {
        super(worldX, worldY);
        this.score = 0;
        superItem = true;
        name = "Carro do Lixo";
        try {
            image = ImageIO.read(new FileInputStream("res/items/boy_up_1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Effect(Game gp){
        int effectEnd = gp.timeRuninng + 10;
        gp.player.speed = gp.player.speed*2;
        

    }
        
}
