package ui;

import utilz.LoadUI;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.PauseButtons.SOUND_SIZE_DEFAULT;

public class SoundButton extends PauseButton{
    private BufferedImage [][] soundIMG;
    private boolean mouseOver,mousePressed;
    private int rowIndex,columnIndex;
    private boolean muted;

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

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }


    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadSoundIMG();
    }

    private void loadSoundIMG() {
        BufferedImage temp= LoadUI.GetPauseAtlas(LoadUI.PAUSE_SOUNDS_BUTTONS);
        soundIMG=new BufferedImage[2][3];
        for (int j = 0; j < soundIMG.length; j++) {
            for (int i = 0; i < soundIMG[j].length; i++) {
                soundIMG[j][i]=temp.getSubimage(i*SOUND_SIZE_DEFAULT,j*SOUND_SIZE_DEFAULT,SOUND_SIZE_DEFAULT,SOUND_SIZE_DEFAULT);
            }
        }
    }
    public void resetBooleans(){
        mouseOver=false;
        mousePressed=false;
    }
    public void update(){
        if (muted){
            rowIndex=1;
        }
        else{
            rowIndex=0;
        }
        columnIndex=0;
        if (mouseOver){
            columnIndex=1;
        }
        if (mousePressed) {
            columnIndex=2;
        }
    }

    public void draw(Graphics g){
        g.drawImage(soundIMG[rowIndex][columnIndex],x,y,width,height,null);
    }

}
