package Object;

import javax.imageio.ImageIO;

public class Obj_key extends SuperObject {
	public Obj_key() {
		name = "key";
		try {
			ObjImage = ImageIO.read(getClass().getClassLoader().getResource("Utilitys/key.png"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		collision = true;
	}

}
