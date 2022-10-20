package ui;

import utilz.LoadUI;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.VolumeSlider.*;


public class VolumeSlider extends PauseButton{
    private BufferedImage[] imgs;
    private BufferedImage slider;
    private int index=0;
    private boolean mouseOver,mousePressed;
    private int buttonX,minX,maxX;

    public VolumeSlider(int x, int y, int width, int height) {
        super(x+width/2, y, VOLUME_WIDTH, height);
        bounds.x-=VOLUME_WIDTH/2;
        buttonX=x+width/2;
        this.x=x;
        this.width=width;
        minX=x+VOLUME_WIDTH/2;
        maxX=x+width-VOLUME_WIDTH/2;
        loadIMG();
    }

    private void loadIMG() {
        BufferedImage temp= LoadUI.GetPauseAtlas(LoadUI.PAUSE_VOLUME_SLIDER);
        imgs=new BufferedImage[3];
        for (int i = 0; i < imgs.length; i++) {
            imgs[i]=temp.getSubimage(VOLUME_DEFAULT_WIDTH*i,0,VOLUME_DEFAULT_WIDTH,VOLUME_DEFAULT_HEIGHT);
        }
        slider=temp.getSubimage(VOLUME_DEFAULT_WIDTH*3,0,SLIDER_DEFAULT_WIDTH,VOLUME_DEFAULT_HEIGHT);
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
        g.drawImage(slider,x,y,width,height,null);
        g.drawImage(imgs[index],buttonX-VOLUME_WIDTH/2,y,VOLUME_WIDTH,height,null );
    }
    public void changeX(int x){
        if(x<minX){
            buttonX=minX;
        }
        else if(x>maxX){
            buttonX=maxX;
        }
        else{
            buttonX=x;
        }
        bounds.x=buttonX-VOLUME_WIDTH/2;

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
