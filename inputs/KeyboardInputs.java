package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {
    int speed=5;
    private GamePanel gamePanel;
    public KeyboardInputs(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                gamePanel.changeXDelta(-speed);
                break;
            case KeyEvent.VK_W:
                gamePanel.changeYDelta(-speed);
                break;
            case KeyEvent.VK_S:
                gamePanel.changeYDelta(speed);
                break;
            case KeyEvent.VK_D:
                gamePanel.changeXDelta(speed);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
