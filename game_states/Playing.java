package game_states;

import entities.Player;
import levels.LevelManager;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static main.Game.SCALE;

public class Playing extends State implements StateMethods {
    private Player player;
    private LevelManager levelManager;

    public Playing(Game game){
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager=new LevelManager(game);
        player = new Player(200,200,(int)(64*SCALE),(int)(40*SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
    }
    public Player getPlayer(){
        return player;
    }

    public void windowFocusLost() {
        player.resetDirBoolean();
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            player.setAttacking(true);
        }
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
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_W:
                player.setUp(true);
                break;
            case KeyEvent.VK_S:
                player.setDown(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_E:
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
                case KeyEvent.VK_ENTER:
                   GameStates.state=GameStates.MENU;
        }
    }

    @Override
    public void KeyRelease(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_W:
                player.setUp(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }
}
