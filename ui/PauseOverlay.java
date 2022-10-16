package ui;

import main.Game;
import utilz.LoadUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.PauseButtons.*;

public class PauseOverlay {
    private BufferedImage backgroundIMG;
    private int bgX,bgY,bgWidth,bgHeight;
    private SoundButton musicButton,sfxButton;
    public PauseOverlay(){
        loadBackground();
        createSoundButtons();
    }

    private void createSoundButtons() {
        int soundX= (int) (500*Game.SCALE);//opziome umiejscowienie
        int musicY= (int) (140*Game.SCALE);//pionowe umiejscowienie
        int sfxY= (int) (180*Game.SCALE);//pionowe umiejscowienie
        musicButton=new SoundButton(soundX,musicY,SOUND_SIZE,SOUND_SIZE);
        sfxButton=new SoundButton(soundX,sfxY,SOUND_SIZE,SOUND_SIZE);
    }

    private void loadBackground() {
        backgroundIMG= LoadUI.GetPauseAtlas(LoadUI.PAUSE_PATH);
        bgWidth= (int) (backgroundIMG.getWidth()* Game.SCALE);
        bgHeight= (int) (backgroundIMG.getHeight()* Game.SCALE);
        bgX=Game.GAME_WIDTH/2-( backgroundIMG.getWidth()/2);
        bgY= (int) (25*Game.SCALE);
    }

    public void update(){

    }
    public void draw(Graphics g){
    g.drawImage(backgroundIMG,bgX,bgY,bgWidth,bgHeight,null);
    musicButton.draw(g);
    sfxButton.draw(g);
    }

    public void mouseDragged(MouseEvent e){

    }

    public void mousePressed(MouseEvent e) {

    }


    public void mouseReleased(MouseEvent e) {

    }


    public void mouseMoved(MouseEvent e) {

    }
}
