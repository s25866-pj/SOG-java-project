package ui;

import game_states.GameStates;
import utilz.LoadIMG;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.Buttons.*;

public class MenuButton {
    private int xPos, yPos,rowIndex, index, xOffsetCenter=B_WIDTH/2;
    private GameStates states;
    private BufferedImage[] imgs;
    private boolean mouseOver=false,mouseClicked=false;
    private Rectangle bounds;

    public MenuButton(int xPos, int yPos, int rowIndex, GameStates states) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.states = states;
        loadImgs();
        initBounds();
    }

    private void initBounds() {
        bounds=new Rectangle(xPos-xOffsetCenter,yPos,B_WIDTH,B_HEIGHT);
    }

    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp= LoadIMG.GetSpriteAtlas(LoadIMG.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++) {
           imgs[i] =temp.getSubimage(i*B_WIDTH_DEFAULT,rowIndex*B_HEIGHT_DEFAULT,B_WIDTH_DEFAULT,B_HEIGHT_DEFAULT);
        }
    }
    public void draw(Graphics g){
        g.drawImage(imgs[index], xPos-xOffsetCenter,yPos,B_WIDTH,B_HEIGHT,null);
    }
    public void update(){
        index=0;
        if(mouseOver){
            index=1;
        }
        if(mouseClicked){
            index=2;
        }
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mouseClicked;
    }

    public void setMousePressed(boolean mouseClicked) {
        this.mouseClicked = mouseClicked;
    }
    public void applyGameState(){
        GameStates.state=states;
    }
    public void resetBooleans(){
        mouseOver=false;
        mouseClicked=false;
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
