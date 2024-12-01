package main;

import Object.Obj_Chest;
import Object.Obj_boot;
import Object.Obj_door;
import Object.Obj_key;

import entity.NPC_Guard;
import entity.REDmON;

public class AssertSetter {
    GamePanel gp;

    public AssertSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        int i = 0;

        // Assuming we are setting objects for a single map now
        gp.obj[i] = new Obj_key();
        gp.obj[i].worldX = 23 * gp.tileSize;
        gp.obj[i].worldY = 7 * gp.tileSize;
        i++;
        gp.obj[i] = new Obj_key();
        gp.obj[i].worldX = 23 * gp.tileSize;
        gp.obj[i].worldY = 40 * gp.tileSize;
        i++;
        gp.obj[i] = new Obj_key();
        gp.obj[i].worldX = 38 * gp.tileSize;
        gp.obj[i].worldY = 8 * gp.tileSize;
        i++;
        gp.obj[i] = new Obj_door();
        gp.obj[i].worldX = 10 * gp.tileSize;
        gp.obj[i].worldY = 11 * gp.tileSize;
        i++;
        gp.obj[i] = new Obj_door();
        gp.obj[i].worldX = 8 * gp.tileSize;
        gp.obj[i].worldY = 28 * gp.tileSize;
        i++;
        gp.obj[i] = new Obj_door();
        gp.obj[i].worldX = 12 * gp.tileSize;
        gp.obj[i].worldY = 22 * gp.tileSize;
        i++;
        gp.obj[i] = new Obj_Chest();
        gp.obj[i].worldX = 10 * gp.tileSize;
        gp.obj[i].worldY = 7 * gp.tileSize;
        i++;
        gp.obj[i] = new Obj_boot();
        gp.obj[i].worldX = 37 * gp.tileSize;
        gp.obj[i].worldY = 41 * gp.tileSize;
    }

    public void setObjectNpc() {
       
        gp.npc[0] = new NPC_Guard(gp);
        gp.npc[0].worldx = gp.tileSize * 21;
        gp.npc[0].worldy = gp.tileSize * 21;
    }
    public void setObjectMonster() {
        
        gp.monster[0] = new REDmON(gp);
        gp.monster[0].worldx = gp.tileSize * 20;
        gp.monster[0].worldy = gp.tileSize * 36;
       
        gp.monster[1] = new REDmON(gp);
        gp.monster[1].worldx = gp.tileSize * 24;
        gp.monster[1].worldy = gp.tileSize * 32;
    }
}
