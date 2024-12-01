package main;

import entity.Entity;

public class CollsionChecker {
	GamePanel gp;

	public CollsionChecker(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldx + entity.SolidArea.x;
		int entityRightWorldX = entity.worldx + entity.SolidArea.x + entity.SolidArea.width;
		int entityTopWorldY = entity.worldy + entity.SolidArea.y;
		int entityBottomWorldY = entity.worldy + entity.SolidArea.y + entity.SolidArea.height;
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;

		int tileNum1, tileNum2;

		switch (entity.direction) {
		case "w":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapNum[entityRightCol][entityTopRow];
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "s":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapNum[entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "a":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapNum[entityLeftCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		case "d":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapNum[entityRightCol][entityBottomRow];
			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			break;
		}
	}

	public int checkObject(Entity entity, boolean player) {
		int index = 999;

		for (int i = 0; i < gp.obj.length; i++) {
			if (gp.obj[i] != null) {
				entity.SolidArea.x = entity.worldx + entity.SolidArea.x;
				entity.SolidArea.y = entity.worldy + entity.SolidArea.y;

				gp.obj[i].SolidArea.x = gp.obj[i].worldX + gp.obj[i].SolidArea.x;
				gp.obj[i].SolidArea.y = gp.obj[i].worldY + gp.obj[i].SolidArea.y;

				switch (entity.direction) {
				case "w":
					entity.SolidArea.y -= entity.speed;
					break;
				case "s":
					entity.SolidArea.y += entity.speed;
					break;
				case "a":
					entity.SolidArea.x -= entity.speed;
					break;
				case "d":
					entity.SolidArea.x += entity.speed;
					break;
				}
				if (entity.SolidArea.intersects(gp.obj[i].SolidArea)) {
					if (gp.obj[i].collision) {
						entity.collisionOn = true;
					}
					if (player) {
						index = i;
					}
				}

				entity.SolidArea.x = entity.SolidAreaObjX;
				entity.SolidArea.y = entity.SolidAreaObjY;

				gp.obj[i].SolidArea.x = gp.obj[i].SolidAreaObjX;
				gp.obj[i].SolidArea.y = gp.obj[i].SolidAreaObjY;
			}
		}
		return index;
	}

	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;

		for (int i = 0; i < target.length; i++) {
			if (target[i] != null) {
				entity.SolidArea.x = entity.worldx + entity.SolidArea.x;
				entity.SolidArea.y = entity.worldy + entity.SolidArea.y;

				target[i].SolidArea.x = target[i].worldx + target[i].SolidArea.x;
				target[i].SolidArea.y = target[i].worldy + target[i].SolidArea.y;

				switch (entity.direction) {
				case "w":
					entity.SolidArea.y -= entity.speed;
					break;
				case "s":
					entity.SolidArea.y += entity.speed;
					break;
				case "a":
					entity.SolidArea.x -= entity.speed;
					break;
				case "d":
					entity.SolidArea.x += entity.speed;
					break;
				}

				if (entity.SolidArea.intersects(target[i].SolidArea)) {
					if (target[i] != entity) {               
						entity.collisionOn = true;
						index = i;
					}
				}

				entity.SolidArea.x = entity.SolidAreaObjX;
				entity.SolidArea.y = entity.SolidAreaObjY;

				target[i].SolidArea.x = target[i].SolidAreaObjX;
				target[i].SolidArea.y = target[i].SolidAreaObjY;
			}
		}
		return index;
	}

	public boolean checkPlayer(Entity entity) {
		boolean contactPlayer=false;
		
		entity.SolidArea.x = entity.worldx + entity.SolidArea.x;
		entity.SolidArea.y = entity.worldy + entity.SolidArea.y;

		gp.playHand.SolidArea.x = gp.playHand.worldx + gp.playHand.SolidArea.x;
		gp.playHand.SolidArea.y = gp.playHand.worldy + gp.playHand.SolidArea.y;

		switch (entity.direction) {
		case "w":
			entity.SolidArea.y -= entity.speed;
			break;
		case "s":
			entity.SolidArea.y += entity.speed;
			break;
		case "a":
			entity.SolidArea.x -= entity.speed;
			break;
		case "d":
			entity.SolidArea.x += entity.speed;
			break;
		}

		if (entity.SolidArea.intersects(gp.playHand.SolidArea)) {
			entity.collisionOn = true;
			contactPlayer=true;
		}

		entity.SolidArea.x = entity.SolidAreaObjX;
		entity.SolidArea.y = entity.SolidAreaObjY;
		gp.playHand.SolidArea.x = gp.playHand.SolidAreaObjX;
		gp.playHand.SolidArea.y = gp.playHand.SolidAreaObjY;
		return contactPlayer;
	}
	

}
