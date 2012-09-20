 import java.util.ArrayList;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

//Christopher de la Iglesia

public class Main {

    GraphicsEngine graphics;
    Keys keys;
    double previous;

    public Main() {
	graphics = new GraphicsEngine(640,480);
	keys = new Keys(graphics);
	previous = System.currentTimeMillis();
    }

    public static void main(String[] args) {
	Main start = new Main();
	start.mainThread();
    }

    public int mainThread() {
	ArrayList<Wall> walls;
	walls = initWalls();
	for(;;) {
	    if((System.currentTimeMillis() - previous) > 10) {

		if(graphics. render(walls, keys.keys) != 0) return -1;
		previous = System.currentTimeMillis();
	    }
	    if(keys.keys[KeyEvent.VK_ESCAPE]) break;
	}
	return 0;
    }

    public ArrayList<Wall> initWalls() {



	ArrayList<Wall> walls = new ArrayList<Wall>();

	int[] pixels = new int[50*50];
	int[] pixels2 = new int[50*50];

	try {
	    File file = new File("../World.png");
	    BufferedImage img = ImageIO.read(file);

	    pixels = new int[img.getWidth()*img.getHeight()];
	    img.getRGB(0,0,img.getWidth(),img.getHeight(),pixels,0,img.getWidth());

	    file = new File("../Wall2.png");
	    img = ImageIO.read(file);

	    pixels2 = new int[img.getWidth()*img.getHeight()];
	    img.getRGB(0,0,img.getWidth(),img.getHeight(),pixels2,0,img.getWidth());
	}
	catch(Exception e) {
	   	System.out.println(e.getMessage());
	}

	Wall wall = new Wall(1.0,1.0,1.0,Math.PI/2,0.0,50,50,pixels);
	Wall wall2= new Wall(-1.0,1.0,1.0,-Math.PI/2,0.0,50,50,pixels2);
	walls.add(wall);
	//walls.add(wall2);

	/*for(int o = 0; o < 10; o++) {
		int[] pixels = new int[50*50];
	    for(int x = 0; x < 50; x++) {
		for(int y = 0; y < 50; y++) {
		    pixels[x + y*50] = 0xFF000000 | rand.nextInt();
		}
	    }
	    int x = rand.nextInt()%10;
	    int y = rand.nextInt()%10;
	    int z = rand.nextInt()%50 + 50;
	    double xrot = rand.nextDouble()*0.1*Math.PI;
	    double yrot = rand.nextDouble()*0.1*Math.PI;
	    Wall wall = new Wall(x,y,z,xrot,yrot,50,50,pixels);
	    walls.add(wall);

	    System.out.println("xrot: " + xrot);
	    System.out.println("yrot: " + yrot);
	    System.out.println();
	}*/

	return walls;

    }

}