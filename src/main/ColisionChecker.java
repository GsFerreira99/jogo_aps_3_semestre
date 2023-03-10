package main;

import entity.Entity;

public class ColisionChecker {

    Game  gp;
    public  ColisionChecker(Game gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity){

        // DEFINE AS POSIÇÕES DE TODAS AS EXTREMIDADES DO BLOCO DE COLISÃO DO JOGADOR
        int entityLeftWorldX = entity.x + entity.solidArea.x;
        int entityRightWorldX = entity.x + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.y + entity.solidArea.y;
        int entityBottomWorldY = entity.y + entity.solidArea.y + entity.solidArea.height;

        // DEFINE AS BORDAS DO BLOCO DE COLISÃO DO JOGADOR
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            // ESTRUTURA QUE VERIFICA SE O PLAYER PODERÁ ANDAR PARA AQUELE TILE BASEANDO-SE EM SUA POSIÇÃO FUTURA
            case "up" -> {
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].colision || gp.tileM.tile[tileNum2].colision) {
                    entity.colisionOn = true;
                }
            }
            case "down" -> {
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].colision || gp.tileM.tile[tileNum2].colision) {
                    entity.colisionOn = true;
                }
            }
            case "left" -> {
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].colision || gp.tileM.tile[tileNum2].colision) {
                    entity.colisionOn = true;
                }
            }
            case "right" -> {
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].colision || gp.tileM.tile[tileNum2].colision) {
                    entity.colisionOn = true;
                }
            }
        }
    }
}
