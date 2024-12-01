package main;

import java.awt.*;

public class EventHandler {
	GamePanel gp;
	Rectangle eventRect;
	int eventRectDefaultX, eventRectDefaultY;


	int boostcouneter = 0;
	boolean boost = false;

	public EventHandler(GamePanel gp) {

		this.gp = gp;
		eventRect = new Rectangle();
		eventRect.x = 25;
		eventRect.y = 23;
		eventRect.width = 20;
		eventRect.height = 20;
		eventRectDefaultX = eventRect.x;
		eventRectDefaultY = eventRect.y;
	}

	public void checkEven() {

		if (hit(27, 15, "d") == true) {
			damagePit();
		}

		if (hit(26, 7, "w") == true) {
			healing();
		}

//		if (hit(0, 26, 14, "d") == true) {
//			teleport();
//		}

		if (boost == true) {
			boostcouneter++;
			if (boostcouneter > 60) {
				boost = false;
				boostcouneter = 0;
			}

		}

	}

	public boolean hit(int eventCol, int eventRow, String requiredDirection) {

		boolean hit = false;

		gp.playHand.SolidArea.x = gp.playHand.worldx + gp.playHand.SolidArea.x;
		gp.playHand.SolidArea.y = gp.playHand.worldy + gp.playHand.SolidArea.y;
		eventRect.x = eventCol * gp.tileSize + eventRect.x;
		eventRect.y = eventRow * gp.tileSize + eventRect.y;

		if (gp.playHand.SolidArea.intersects(eventRect)) {
			if (gp.playHand.direction.contentEquals(requiredDirection) || requiredDirection.contentEquals("any")) {
				hit = true;

			}
		}

		gp.playHand.SolidArea.x = gp.playHand.SolidAreaObjX;
		gp.playHand.SolidArea.y = gp.playHand.SolidAreaObjY;
		eventRect.x = eventRectDefaultX;
		eventRect.y = eventRectDefaultY;

		return hit;
	}

	public void damagePit() {
		if (gp.playHand.invincible == false) {
			gp.maxlife--;
		
			gp.playHand.invincible = true;
		}
	}

	public void healing() {
		if (boost == false) {
			if (gp.maxlife >= 6) {
				gp.maxlife = 6;
				gp.ui.showMessage("full energy");
			} else {
				gp.maxlife = gp.maxlife + 1;
				
			}
			boost = true;
		}

	}

	public void teleport() {
		gp.playHand.worldx = gp.tileSize * 37;
		gp.playHand.worldy = gp.tileSize * 10;

	}

	public void goToMap(int map) {
		System.out.println("teleport");

	}

}
