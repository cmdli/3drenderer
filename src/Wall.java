//Christopher de la Iglesia

public class Wall {

    private double x, y, z;
    private int width,height;
    private Bitmap sprite;
    private double xrot,yrot;

    public Wall(double x, double y, double z, double xrot, double yrot, int width, int height, int[] pixels) {
	this.x = x;
	this.y = y;
	this.z = z;
	this.width = width;
	this.height = height;
	this.xrot = xrot;
	this.yrot = yrot;
	if(pixels != null) {
	    sprite = new Bitmap(null,width,height,pixels);
	}
	else {
	    sprite = new Bitmap(null,width,height,null);
	}
    }

    public int draw(Bitmap screen, double xcam, double ycam, double zcam, double xcamrot, double ycamrot, boolean debug) {

	if(screen.draw3DWall(x-xcam,y-ycam,z-zcam,xrot+xcamrot,yrot+ycamrot,sprite,debug) != 0) return -1;

	return 0;

    }
    
    public void pointSprite(Bitmap screen) {
	sprite.pixels = screen.pixels;
    }

}