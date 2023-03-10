package main;

public class Level {
    int numLevel;
    String mapFile;
    Game gp;
    public Level(Game gp, int numLevel, String mapFile){
        this.gp = gp;
        this.numLevel = numLevel;
        this.mapFile = mapFile;
    }
}
