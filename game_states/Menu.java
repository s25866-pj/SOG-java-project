package game_states;

import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends State implements StateMethods {
    public Menu(Game game) {
        super(game);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawString("Menu",Game.GAME_WIDTH/2,200);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void KeyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            GameStates.state=GameStates.PLAYING;
        }
    }

    @Override
    public void KeyRelease(KeyEvent e) {

    }
}
