//Christopher de la Iglesia

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GraphicsEngine extends JFrame {

    private double xscale, yscale;
    private int width, height;
    private Graphics draw, drawToScreen;
    private BufferedImage drawingBoard;
    private Bitmap screen;
    private int[] pixels, bg;
    private double xview,yview;
    private double xcam,ycam,zcam,xcamrot,ycamrot;
    private int dist;
    private boolean going;
    private Wall test;
    private double xspeed, yspeed, zspeed;

    public GraphicsEngine(int width, int height) {
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
		xcam = 0.0;
		ycam = 0.0;
		zcam = 0.0;
		xcamrot = 0.0;
		ycamrot = 0.0;
		xspeed = 0.5;
		yspeed = 0.5;
		zspeed = 0.5;
		xview = Math.PI/4;
		yview = Math.PI/4;
		dist = 0;
		going = true;

		bg = new int[width*height];
		Random rnd = new Random();
		for(int x = 0; x < width; x++) {
		    for(int y = 0; y < height; y++) {
				bg[x + y*width] = (x + y) | 0xFF000001;
		    }
		}

		screen = new Bitmap(null,width,height,null);
		screen.pixels = pixels;
    }

    public int render(ArrayList<Wall> walls, boolean[] keys) {

		if(keys[KeyEvent.VK_Q]) {
		    ycam += yspeed;
		}
		if(keys[KeyEvent.VK_E]) {
		    ycam -= yspeed;
		}
		if(keys[KeyEvent.VK_A]) {
		    xcam -= xspeed;
		}
		if(keys[KeyEvent.VK_D]) {
		    xcam += xspeed;
		}
		if(keys[KeyEvent.VK_W]) {
		    zcam += zspeed;
		}
		if(keys[KeyEvent.VK_S]) {
		    zcam -= zspeed;
		}
		if(keys[KeyEvent.VK_J]) {
			xcamrot += 0.01;
		}
		if(keys[KeyEvent.VK_K]) {
			xcamrot -= 0.01;
		}

	    for(int x = 0; x < width; x++) {
	    	for(int y = 0; y < height; y++) {
		    	screen.pixels[x + y*screen.width] = bg[x +y*width];
	    	}
	    }

		for(int i = 0; i < walls.size(); i++) {
		    if(walls.get(i).draw(screen,xcam,ycam,zcam,xcamrot,ycamrot,keys[KeyEvent.VK_SPACE]) != 0) return -1;
		}

		drawToScreen.drawImage(drawingBoard,0, 30, null);

		return 0;

    }

}