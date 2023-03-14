package main;

import entity.Entity;
import items.SuperItem;

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

    public int checkObject(Entity entity, boolean player) {
        int index = 999;
        SuperItem[] items = gp.levelManager.getActiveLevel().items;
        for(int i=0; i<items.length; i++){
            if (items[i] != null){
                entity.solidArea.x = entity.x + entity.solidArea.x;
                entity.solidArea.y = entity.y + entity.solidArea.y;

                items[i].solidArea.x = items[i].worldX + items[i].solidArea.x;
                items[i].solidArea.y = items[i].worldY + items[i].solidArea.y;

                switch (entity.direction) {
                    case "up" -> {
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(items[i].solidArea)) {
                            if(items[i].colision){
                                entity.colisionOn = true;
                            }
                            if(player){
                                index=i;
                            }
                        }
                    }
                    case "down" -> {
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(items[i].solidArea)) {
                            if(items[i].colision){
                                entity.colisionOn = true;
                            }
                            if(player){
                                index=i;
                            }
                        }
                    }
                    case "left" -> {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(items[i].solidArea)) {
                            if(items[i].colision){
                                entity.colisionOn = true;
                            }
                            if(player){
                                index=i;
                            }
                        }
                    }
                    case "right" -> {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(items[i].solidArea)) {
                            if(items[i].colision){
                                entity.colisionOn = true;
                            }
                            if(player){
                                index=i;
                            }
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                if (items[i] != null){
                    items[i].solidArea.x = items[i].solidAreaDefaultX;
                    items[i].solidArea.y = items[i].solidAreaDefaultY;
                }else{
                    gp.levelManager.getActiveLevel().contadorLixos--;
                }
            }
        }
        return index;
    }
}
