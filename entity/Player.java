package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;
	public int hashKey = 0;
	Graphics2D g3;

	public Player(GamePanel gp, KeyHandler keyH) {

		super(gp);

		this.gp = gp;
		this.keyH = keyH;

		screenX = gp.ScreenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.ScreenHeight / 2 - (gp.tileSize / 2);

		SolidArea = new Rectangle();
		SolidArea.x = 8;
		SolidArea.y = 16;

		SolidAreaObjX = SolidArea.x;
		SolidAreaObjY = SolidArea.y;

		SolidArea.width = 32;
		SolidArea.height = 32;

		setDefaultValues();
		getPlayerImages();
	}

	public void setDefaultValues() {
		worldx = gp.tileSize * 23;
		worldy = gp.tileSize * 21;
	}

	public void getPlayerImages() {
		body1 = setPlayerImage("Utilitys/body1.png", gp.tileSize, gp.tileSize);
		body2 = setPlayerImage("Utilitys/body2.png", gp.tileSize, gp.tileSize);
		bodyback1 = setPlayerImage("Utilitys/bodyback1.png", gp.tileSize, gp.tileSize);
		bodyback2 = setPlayerImage("Utilitys/bodyback2.png", gp.tileSize, gp.tileSize);
		left = setPlayerImage("Utilitys/right.png", gp.tileSize, gp.tileSize);
		right = setPlayerImage("Utilitys/right.png", gp.tileSize, gp.tileSize);

		attackImageUp1 = setPlayerImage("Utilitys/swordfront.png", gp.tileSize, gp.tileSize * 2);
		attackImageback1 = setPlayerImage("Utilitys/swordback.png", gp.tileSize, gp.tileSize * 2);

	}

	public void update() {

		if (attacking == true) {

			attackingMethod();

		}

		else if (keyH.upPress == true || keyH.downPress == true || keyH.leftPress == true || keyH.rightPress == true) {
			if (keyH.leftPress) {

				direction = "a";
			}
			if (keyH.rightPress) {

				direction = "d";
			}
			if (keyH.upPress) {

				direction = "w";
			}
			if (keyH.downPress) {

				direction = "s";
			}
			collisionOn = false;
			gp.colc.checkTile(this);

			int ObjIndex = gp.colc.checkObject(this, true);
			pickUpObject(ObjIndex);

			int npcIndex = gp.colc.checkEntity(this, gp.npc);
			interActNpc(npcIndex);
			int monterIndex = gp.colc.checkEntity(this, gp.monster);
			interActMonster(monterIndex);

			gp.evenH.checkEven();

			if (collisionOn == false) {
				switch (direction) {
				case "w":
					worldy -= speed;
					break;
				case "s":
					worldy += speed;
					break;
				case "a":
					worldx -= speed;
					break;
				case "d":
					worldx += speed;
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + direction);
				}
			}

			spiritCount++;
			if (spiritCount > 10) {
				if (spirit == 1) {
					spirit = 2;
				} else if (spirit == 2) {
					spirit = 1;
				}
				spiritCount = 0;
			}

			if (invincible == true) {
				invincibleCounter++;
				if (invincibleCounter > 60) {
					invincible = false;
					invincibleCounter = 0;
				}
			}

		

		}
	}

	public void interActNpc(int i) {
		if (i != 999) {
			gp.ui.showMessage("Hi go out from this area  it is dangerous");
		} else {
			if (gp.keyH.enterPressed == true) {
				attacking = true;
			}
		}
	}

	public void interActMonster(int i) {
		if (i != 999) {
			if (invincible == false) {
				gp.maxlife--;
				invincible = true;
			}

		}
	}

	public void attackingMethod() {
		spiritCount++;
		if (spiritCount <= 5) {
			spirit = 1;

		}
		if (spiritCount > 5 && spiritCount <= 25) {
			spirit = 2;

			int monsterINdex = gp.colc.checkEntity(this, gp.monster);
			damageMonster(monsterINdex);

		}
		if (spiritCount > 10) {
			spirit = 1;
			spiritCount = 0;
			attacking = false;
		}
	}

	public void pickUpObject(int i) {
		if (i != 999) {
			String objectName = gp.obj[i].name;

			switch (objectName) {
			case "key":
				hashKey++;
				gp.obj[i] = null;
				gp.ui.showMessage("You found a key! Beware...");
				break;
			case "door":
				if (hashKey != 0) {
					gp.obj[i] = null;
					hashKey--;
					gp.ui.showMessage("You opened the door...");
				} else {
					gp.ui.showMessage("The door is locked! You need a key...");
				}
				break;
			case "Chest":
				gp.obj[i] = null;
				gp.ui.showMessage("You opened the chest. ");
				break;
			case "boot":
				gp.obj[i] = null;
				speed += 2;
				gp.ui.showMessage("You feel faster...");
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + objectName);
			}
		}
	}

	public void damageMonster(int i) {
		if (i != 999 && keyH.enterPressed == true) {
			gp.monster[i].maxHealth -= 1;
			if (gp.monster[i].life <= 20) {
			
				gp.monster[i].life += 1;
			}
			if (gp.monster[i].maxHealth <= 0) {
				gp.monster[i] = null;
			}

		}
	}

	public void draw(Graphics2D g2) {

		int tempoX = screenX;
		int tempoY = screenY;

		Image PlayerImage = null;
		switch (direction) {
		case "w":

			if (attacking == false) {
				PlayerImage = (spirit % 2 == 1) ? bodyback1 : bodyback2;
			} else {
				tempoY += gp.tileSize;
				PlayerImage = (spirit % 2 == 1) ? attackImageback1 : attackImageback1;
			}

			break;
		case "s":
			if (attacking == false) {
				PlayerImage = (spirit % 2 == 1) ? body1 : body2;
			} else {

				PlayerImage = (spirit % 2 == 1) ? attackImageUp1 : attackImageUp1;
			}
			break;
		case "a":

			PlayerImage = left;

			break;
		case "d":

			PlayerImage = right;

			break;
		default:

			PlayerImage = body1;
			break;
		}
		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}

		if (attacking == false) {
			g2.drawImage(PlayerImage, tempoX, tempoY, gp.tileSize, gp.tileSize, null);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		} else {
			if (direction == "w" || direction == "s") {
				g2.drawImage(PlayerImage, tempoX, tempoY, 48, 96, null);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			} else {
				g2.drawImage(PlayerImage, tempoX, tempoY, gp.tileSize, gp.tileSize, null);
				g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
			}
		}

	}

}
