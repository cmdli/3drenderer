//Christopher de la Iglesia

public class Polygon {

    private int[] x;
    private int[] y;

    public Polygon(int[] x, int[] y) {
	this.x = new int[3];
	this.y = new int[3];

	if(x != null) {
	    this.x = x;
	}
	if(y != null) {
	    this.y = y;
	}
    }

}