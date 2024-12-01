package Object;

import javax.imageio.ImageIO;



public class Obj_heart extends SuperObject {

	
	
	
	public Obj_heart() {
		name = "heart";
		try {
			ObjImage1 = ImageIO.read(getClass().getClassLoader().getResource("Utilitys/fullheart.png"));
			ObjImage2= ImageIO.read(getClass().getClassLoader().getResource("Utilitys/halfheart.png"));
			ObjImage3 = ImageIO.read(getClass().getClassLoader().getResource("Utilitys/noheart.png"));
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
