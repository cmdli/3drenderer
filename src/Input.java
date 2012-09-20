//Christopher de la Iglesia

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Input {

    File file;

    public Input(String name) {
	file = new File(name);
    }

    public int[] loadSprite() {
    	BufferedImage image = null;
    	
    	try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    	return ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    	
    }

}