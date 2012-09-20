//Christopher de la Iglesia

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.Color;
import java.awt.event.*;

public class OldGraphicsEngine extends JFrame {
    
    double xscale, yscale;
    int width, height;
    Graphics draw, drawToScreen;
    BufferedImage drawingBoard;
    int[] pixels;
    double xview,yview;
    int xcam,ycam,zcam;
    int dist;
    boolean going;

    public OldGraphicsEngine(int width, int height) {
	super("Engine");

	this.width = width;
	this.height = height;

	super.setSize(this.width,this.height);
	super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	super.setVisible(true);

	pixels = new int[this.width*this.height];
	drawToScreen = this.getGraphics();
	drawingBoard = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_ARGB);
	draw = drawingBoard.getGraphics();
	pixels = ((DataBufferInt) drawingBoard.getRaster().getDataBuffer()).getData();
	xcam = 0;
	ycam = 0;
	zcam = 0;
	xview = Math.PI;
	yview = Math.PI;
	dist = 0;
	going = true;
    }

    public int render() {
	
	if(dist >= 50) going = false;
	if(going) dist += 5;
	if(!going) dist -= 5;
	if(dist <= 5) going = true;
	System.out.println(dist + " ");
	System.out.print(going + " ");

	draw.setColor(Color.black);
	draw.fillRect(0,0,500,500);

	draw.setColor(Color.gray);
	draw.drawLine(width/2,0,width/2,height);
	draw.drawLine(0,height/2,width,height/2);
	draw.setColor(Color.black);

	for(int x = -50; x < 50; x++) {
	    for(int y = -50; y < 50; y++) {
		drawPixel(x,y,50+dist,0xFF00FFFF);
	    }
	}

	draw.setColor(Color.white);
	draw.drawString(Integer.toString(dist),50,50);

	drawToScreen.drawImage(drawingBoard,0, 30, null);

	return 0;

    }
    

    public int drawPixel(int x, int y, int z, int col) {
	
	double xd = Math.atan(((double)(x - xcam))/((double)(z - zcam)));
	double yd = Math.atan((((double)(y - ycam))/((double)(z - zcam))));
	int xpixel = (int)((xd/xview)*((double)(width/2))) + width/2;
	int ypixel = (int)((yd/yview)*((double)(height/2))) + height/2;

	if(x == -50 && y == 0) {
	    System.out.print("(" + x + ",");
	    System.out.print(y + ",");
	    System.out.print(z + ",");
	    System.out.print(xd + ",");
	    System.out.print(yd + ",");
	    System.out.print(xpixel + ",");
	    System.out.print(ypixel + ")      ");
	}

	pixels[xpixel + ypixel*width] = col;
	return 0;

    }

}