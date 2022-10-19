package ui;

import utilz.LoadUI;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.URMButtons.*;

public class UrmButtons extends PauseButton {
    private BufferedImage [] imgs;
    private int rowIndex,index;
    private boolean mouseOver;
    private boolean mousePressed;
    public UrmButtons(int x, int y, int width, int height, int rowIndex){
        super(x, y, width, height);
        this.rowIndex=rowIndex;
        loadIMG();
    }

    private void loadIMG() {
        BufferedImage temp= LoadUI.GetPauseAtlas(LoadUI.PAUSE_URM_BUTTONS);
        imgs=new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++) {
            imgs[i]=temp.getSubimage(UMR_DEFAULT_SIZE*i,UMR_DEFAULT_SIZE*rowIndex,UMR_DEFAULT_SIZE,UMR_DEFAULT_SIZE);
        }
    }

    public void update(){
        index=0;
        if(mouseOver){
            index=1;
        }
        if(mousePressed){
            index=2;
        }


    }
    public void draw(Graphics g){
        g.drawImage(imgs[index],x,y,UMR_SIZE,UMR_SIZE,null );
    }
    public void resetBooleans(){
        mousePressed=false;
        mouseOver=false;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
