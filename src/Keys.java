//Christopher de la Iglesia
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class Keys implements KeyListener {

    public boolean[] keys = new boolean[65536];

    public Keys(JFrame window) {
	window.addKeyListener(this);
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
	keys[e.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent e) {
	keys[e.getKeyCode()] = false;
    }

}