package Object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject {
	public BufferedImage ObjImage;
	public BufferedImage ObjImage1,ObjImage2,ObjImage3 ;//this images use in Obj_heart class check it
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
 
	public Rectangle SolidArea = new Rectangle(0, 0, 48, 48);
	public int SolidAreaObjX = 0;
	public int SolidAreaObjY = 0;

	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = worldX - gp.playHand.worldx + gp.playHand.screenX;
		int screenY = worldY - gp.playHand.worldy + gp.playHand.screenY;

		if (worldX + gp.tileSize > gp.playHand.worldx - gp.playHand.screenX
				&& worldX - gp.tileSize < gp.playHand.worldx + gp.playHand.screenX
				&& worldY + gp.tileSize > gp.playHand.worldy - gp.playHand.screenY
				&& worldY - gp.tileSize < gp.playHand.worldy + gp.playHand.screenY) {

			g2.drawImage(ObjImage, screenX, screenY, gp.tileSize, gp.tileSize, null);

			
			
		}
	}
}
