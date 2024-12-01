package Object;

import javax.imageio.ImageIO;

public class Obj_Chest extends SuperObject {
	public Obj_Chest() {
		name = "Chest";
		try {
			ObjImage = ImageIO.read(getClass().getClassLoader().getResource("Utilitys/box.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
