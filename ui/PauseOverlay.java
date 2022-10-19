package ui;

import game_states.GameStates;
import game_states.Playing;
import main.Game;
import utilz.LoadUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.PauseButtons.*;
import static utilz.Constants.UI.URMButtons.*;

public class PauseOverlay {
    private Playing playing;
    private BufferedImage backgroundIMG;
    private int bgX,bgY,bgWidth,bgHeight;
    private SoundButton musicButton,sfxButton;
    private UrmButtons menuB,replayB,unpauseB;
    public PauseOverlay(Playing playing){
        this.playing=playing;
        loadBackground();
        createSoundButtons();
        createUmrButtons();
    }

    private void createUmrButtons() {
        int X_pos=370;
        int UMRB_Y= (int) (300*Game.SCALE);
        int menuB_X= (int) (X_pos*Game.SCALE);
        int replayB_X= (int) ((X_pos+60)*Game.SCALE);
        int unpauseB_X= (int) ((X_pos+120)*Game.SCALE);
        menuB=new UrmButtons(menuB_X,UMRB_Y,UMR_SIZE,UMR_SIZE,2);
        replayB=new UrmButtons(replayB_X,UMRB_Y,UMR_SIZE,UMR_SIZE,1);
        unpauseB=new UrmButtons(unpauseB_X,UMRB_Y,UMR_SIZE,UMR_SIZE,0);
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
        //sounds buttons
        musicButton.update();
        sfxButton.update();
        //UMR buttons
        menuB.update();
        replayB.update();
        unpauseB.update();
    }
    public void draw(Graphics g){
    g.drawImage(backgroundIMG,bgX,bgY,bgWidth,bgHeight,null);
    //sounds buttons
    musicButton.draw(g);
    sfxButton.draw(g);
    //umr buttons
    menuB.draw(g);
    replayB.draw(g);
    unpauseB.draw(g);
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
        else if(isIn(e,menuB)){
            menuB.setMousePressed(true);
        }
        else if(isIn(e,replayB)){
            replayB.setMousePressed(true);
        }
        else if(isIn(e,unpauseB)){
            unpauseB.setMousePressed(true);
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
        else if(isIn(e,menuB)){
            if(menuB.isMousePressed()){
                GameStates.state =GameStates.MENU;
                playing.unpauseGame();
            }
        }
        else if(isIn(e,replayB)){
            if(replayB.isMousePressed()){
                System.out.println("reset game");
            }
        }
        else if(isIn(e,unpauseB)){
            if(unpauseB.isMousePressed()){
                playing.unpauseGame();
            }
        }
        menuB.resetBooleans();
        replayB.resetBooleans();
        unpauseB.resetBooleans();
        musicButton.resetBooleans();
        sfxButton.resetBooleans();
    }


    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);

        if(isIn(e,musicButton)){
            musicButton.setMouseOver(true);
        }
        else if(isIn(e,sfxButton)){
            sfxButton.setMouseOver(true);
        }
        else if(isIn(e,menuB)){
            menuB.setMouseOver(true);
        }
        else if(isIn(e,replayB)){
            replayB.setMouseOver(true);
        }
        else if(isIn(e,unpauseB)){
            unpauseB.setMouseOver(true);
        }
    }
    private boolean isIn(MouseEvent e,PauseButton b){
        return b.getBounds().contains(e.getX(),e.getY());
    }
}
