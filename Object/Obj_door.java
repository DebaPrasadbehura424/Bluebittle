package Object;

import javax.imageio.ImageIO;

public class Obj_door extends SuperObject {
	public Obj_door() {
		name = "door";
		try {
			ObjImage = ImageIO.read(getClass().getClassLoader().getResource("Utilitys/door.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		collision = true;
	}

}
