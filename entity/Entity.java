package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import main.GamePanel;

public class Entity {
	GamePanel gp;
	// character info
	public int worldx;
	public int worldy;
	public String direction = "d";

	// image declare
	public Image body1, body2, bodyback1, bodyback2, left, right;
	public Image attackImageUp1, attackImageback1, attackImageleft, attackImageRight;

	// my speed and spirit
	public int speed = 4;
	public int spirit = 1;
	public int maxHealth = 20;
	public int life = 1;

	// counter for changes
	public int invincibleCounter = 0;
	int spiritCount = 0;
	public int dyingCounter = 0;


	// Make default solid area
	public Rectangle SolidArea = new Rectangle(0, 0, 48, 48);
	public int SolidAreaObjX, SolidAreaObjY;

	// extra features
	public boolean collisionOn = false;
	public boolean invincible = false;
	boolean attacking = false;
	public boolean dying = false;
	public boolean alive = true;


	public int type;

	public Entity(GamePanel gp) {
		this.gp = gp;

	}

	public void setMovementNpc() {
	};

	public void setMovementMonster() {

	}

	public void damageReaction() {

	}

	public void update() {
		setMovementNpc();
		setMovementMonster();

		collisionOn = false;
		gp.colc.checkTile(this);
		gp.colc.checkObject(this, false);
		gp.colc.checkEntity(this, gp.npc);
		gp.colc.checkEntity(this, gp.monster);
		boolean ContactPlayer = gp.colc.checkPlayer(this);
		if (this.type == 2 && ContactPlayer == true) {
			if (gp.playHand.invincible == false) {
				gp.maxlife--;
				gp.playHand.invincible = true;
				gp.ui.showMessage("oouch!");
			}
		}

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
	}

	public void drawNpc(Graphics2D g2) {
		Image NpcPlayer = null;

		int screenX = worldx - gp.playHand.worldx + gp.playHand.screenX;
		int screenY = worldy - gp.playHand.worldy + gp.playHand.screenY;

		if (worldx + gp.tileSize > gp.playHand.worldx - gp.playHand.screenX
				&& worldx - gp.tileSize < gp.playHand.worldx + gp.playHand.screenX
				&& worldy + gp.tileSize > gp.playHand.worldy - gp.playHand.screenY
				&& worldy - gp.tileSize < gp.playHand.worldy + gp.playHand.screenY) {

			switch (direction) {
			case "w":
				NpcPlayer = (spirit % 2 == 1) ? bodyback1 : bodyback2;
				break;
			case "s":
				NpcPlayer = (spirit % 2 == 1) ? body1 : body2;
				break;
			case "a":
				NpcPlayer = left;
				break;
			case "d":
				NpcPlayer = right;
				break;
			default:
				NpcPlayer = body1;
				break;
			}

			if (type == 2) {

				double onScale = (double) gp.tileSize / life;
			

				g2.setColor(new Color(35, 35, 35));
				g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);
				g2.setColor(new Color(255, 0, 30));

				g2.fillRect(screenX, screenY - 15, (int) onScale, 10);

			}
			g2.drawImage(NpcPlayer, screenX, screenY, gp.tileSize, gp.tileSize, null);
			// you can call your dying animation form here

		}
	}

	public Image setPlayerImage(String ImagePath, int imageWindth, int imageHeight) {
		Image IoImage = null;
		IoImage = new ImageIcon(getClass().getClassLoader().getResource(ImagePath)).getImage();
		return IoImage;
	}

}
