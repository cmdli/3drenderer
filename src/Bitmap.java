//Christopher de la Iglesia

public class Bitmap {

    int[] pixels;
    double[] db;
    int width;
    int height;
    double xView;
    double yView;

    public Bitmap(String name, int xSize, int ySize, int[] pixels) {
	if(name == null) {
	    if(pixels != null) {
		this.pixels = pixels;
	    }
	    else {
		this.pixels = new int[xSize*ySize];
	    }
	}
	else {
	    Input input = new Input(name);
	    this.pixels = input.loadSprite();
	    if(pixels == null) {
    		System.out.println("Bitmap " + name + " not loaded!");
		this.pixels = new int[xSize*ySize];
	    }
	}

	width = xSize;
	height = ySize;
	xView = Math.PI/4.0;
	yView = Math.PI/4.0;
	db = new double[width*height];
	for(double d : db) {
		d = Math.PI/4.0-1.0;
	}
    }

    public int draw(int xloc, int yloc, Bitmap input) {

    	for(int x = xloc; x < input.width; x++) {
    		for(int y = yloc; y < input.height; y++) {
    			pixels[x + y*width] = input.pixels[x + y*input.width];
    		}
    	}

    	return 0;
    }

    public int draw3DWall(double xloc, double yloc, double zloc, double xrot, double yrot, Bitmap input,boolean debug) {


    	double xTop = Math.atan(xloc/zloc);
    	double yTop = Math.atan(yloc/zloc);
    	double xBot = Math.atan((xloc + ((double)input.width)*Math.cos(xrot))/(zloc+((double)input.width)*Math.sin(xrot)));
    	double yBot = Math.atan((yloc + ((double)input.height)*Math.cos(yrot))/(zloc+((double)input.height)*Math.sin(yrot)));

    	if(debug) {
	    System.out.println("old xTop: " + xTop);
	    System.out.println("old yTop: " + yTop);
	    System.out.println("old xBot: " + xBot);
	    System.out.println("old yBot: " + yBot);
	    System.out.println("new xBotLoc: " + (xloc + ((double)input.width)*Math.cos(xrot)));
	    System.out.println("new zBotLoc: " + (zloc + ((double)input.height)*Math.sin(xrot)));

    	}

	xTop = (xTop/xView)*((double)(width/2.0)) + (double)width/2.0;
	yTop = (yTop/yView)*((double)(height/2.0)) + (double)height/2.0;
	xBot = (xBot/xView)*((double)(width/2.0)) + (double)width/2.0;
	yBot = (yBot/yView)*((double)(height/2.0)) + (double)height/2.0;



    	double xscale = Math.abs(((double)input.width-1.0)/(xTop-xBot));
    	double yscale = Math.abs(((double)input.height-1.0)/(yTop-yBot));

	if(debug) {
	    System.out.println("xTop: " + xTop);
	    System.out.println("yTop: " + yTop);
	    System.out.println("xBot: " + xBot);
	    System.out.println("yBot: " + yBot);
	    System.out.println("xrot: " + xrot);
	    System.out.println("yrot: " + yrot);
	    System.out.println("xloc: " + xloc);
	    System.out.println("yloc: " + yloc);
	    System.out.println("zloc: " + zloc);
	    System.out.println();
	}



    	for(int x = 0; x < (xBot-xTop); x++) {
	    for(int y = 0; y < (yBot-yTop); y++) {
		try {
		    if(!(x + (int) xTop > this.width - 1.0 || y + (int)yTop > this.height - 1.0) && !(x+(int)xTop < 0 || y+(int)yTop < 0)) {
			/*double dist = Math.sqrt(xloc*xloc + yloc*yloc + zloc*zloc);

			if(dist >= input.db[(x + (int)xTop) + ((y+(int)yTop)*this.width)]) {
			    db[(x+(int)xTop) + ((y+(int)yTop)*this.width)] = dist;*/
			    this.pixels[(x+(int)xTop) + ((y+(int)yTop)*this.width)] = input.pixels[(int)(x*xscale) + ((int)(y*yscale))*input.width];
			//}
		    }
		}
		catch(Exception e) {
		    System.out.println("Error! Error!");
		    System.out.println("x: " + (x + xTop));
		    System.out.println("y: " + (y + yTop));
		    System.out.println("xTop: " + xTop);
		    System.out.println("yTop: " + yTop);
		    System.out.println("xrot: " + xrot);
		    System.out.println("yrot: " + yrot);
		    System.out.println("xloc: " + xloc);
		    System.out.println("yloc: " + yloc);
		    System.out.println("zloc: " + zloc);
		    e.printStackTrace();
		    return -1;
		}
	    }
    	}

    	return 0;
    }

    public void drawSquarePolygon(Point one, Point two, Point three, Point four, int[] pixels) {
	
    }

}