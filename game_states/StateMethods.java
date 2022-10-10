package game_states;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {
    public void update();
    public void draw(Graphics g);
    public void mouseClicked(MouseEvent e);
    public void mousePressed(MouseEvent e);
    public void mouseReleased(MouseEvent e);
    public void mouseMoved(MouseEvent e);
    public void KeyPressed(KeyEvent e);
    public void KeyRelease(KeyEvent e);

}
