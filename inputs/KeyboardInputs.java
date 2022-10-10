package inputs;

import game_states.GameStates;
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
        switch (GameStates.state) {
            case PLAYING -> {
                gamePanel.getGame().getPlaying().KeyPressed(e);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().KeyPressed(e);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameStates.state) {
            case PLAYING -> {
                gamePanel.getGame().getPlaying().KeyRelease(e);
            }
            case MENU -> {
                gamePanel.getGame().getMenu().KeyRelease(e);
            }
        }

    }
}
