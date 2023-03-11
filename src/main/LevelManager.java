package main;

import java.io.IOException;

public class LevelManager {
    public Level[] levels = new Level[6];
    Game gp;
    int levelActive = 1;
    public LevelManager(Game gp){
        this.gp = gp;
        defineLevels();
    }

    public void defineLevels(){
        levels[0] = new Level(this.gp, 1, "res/maps/fase_1.txt");
        levels[1] = new Level(this.gp, 2, "res/maps/fase_2.txt");
    }

    public void setLevel(int level){
        levelActive = level;
    }

    public String getLevelMap() {
        return levels[levelActive-1].mapFile;
    }

    public Level getActiveLevel(){
        return levels[levelActive-1];
    }
}
