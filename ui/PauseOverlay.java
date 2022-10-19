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
        musicButton.update();
        sfxButton.update();
    }
    public void draw(Graphics g){
    g.drawImage(backgroundIMG,bgX,bgY,bgWidth,bgHeight,null);
    musicButton.draw(g);
    sfxButton.draw(g);
    }

    public void mouseDragged(MouseEvent e){

    }

    public void mousePressed(MouseEvent e) {
        if(isIn(e,musicButton)){
            musicButton.setMousePressed(true);
        }
        else if(isIn(e,sfxButton)){
            sfxButton.setMousePressed(true);
        }
    }


    public void mouseReleased(MouseEvent e) {
        if(isIn(e,musicButton)){
            if(musicButton.isMousePressed()){
                musicButton.setMuted(!musicButton.isMuted());
            }

        }
        else if(isIn(e,sfxButton)){
            if(sfxButton.isMousePressed()){
                sfxButton.setMuted(!sfxButton.isMuted());
            }

        }
        musicButton.resetBooleans();
        sfxButton.resetBooleans();
    }


    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);

        if(isIn(e,musicButton)){
            musicButton.setMouseOver(true);
        }
        else if(isIn(e,sfxButton)){
            sfxButton.setMouseOver(true);
        }
    }
    private boolean isIn(MouseEvent e,PauseButton b){
        return b.getBounds().contains(e.getX(),e.getY());


    }
}
