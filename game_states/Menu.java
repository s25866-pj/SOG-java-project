package game_states;

import main.Game;
import utilz.LoadIMG;
import ui.MenuButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethods {
    private MenuButton[] buttons=new MenuButton[3];
    private BufferedImage menuBackground;
    private int menuX,menuY,menuWidth, menuHeight;
    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        menuBackground= LoadIMG.GetSpriteAtlas(LoadIMG.MENU_BACKGROUND);
        menuWidth= (int) (menuBackground.getWidth()*Game.SCALE);
        menuHeight= (int) (menuBackground.getHeight()*Game.SCALE);
        menuX=Game.GAME_WIDTH/2-menuWidth/2;
        menuY= (int) (45*Game.SCALE);
    }

    private void loadButtons() {
        buttons[0]=new MenuButton(Game.GAME_WIDTH/2,(int)(150*Game.SCALE),0,GameStates.PLAYING);
        buttons[1]=new MenuButton(Game.GAME_WIDTH/2,(int)(220*Game.SCALE),1,GameStates.OPTIONS);
        buttons[2]=new MenuButton(Game.GAME_WIDTH/2,(int)(290*Game.SCALE),2,GameStates.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton mb:buttons){
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(menuBackground,menuX,menuY,menuWidth,menuHeight,null);
        for(MenuButton mb:buttons){
           mb.draw(g);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton mb:buttons){
            if (isIn(e,mb)){
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton mb:buttons){
            if (isIn(e,mb)){
                if(mb.isMousePressed()){
                    mb.applyGameState();
                    break;
                }
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for(MenuButton mb:buttons){
            mb.resetBooleans();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton mb:buttons){
            mb.setMouseOver(false);
        }
        for(MenuButton mb:buttons){
            if(isIn(e,mb)){
                mb.setMouseOver(true);
                break;
            }
        }
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
