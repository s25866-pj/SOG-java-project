package ui;

import utilz.LoadUI;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilz.Constants.UI.PauseButtons.SOUND_SIZE_DEFAULT;

public class SoundButton extends PauseButton{
    private BufferedImage [][] soundIMG;
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
    public void update(){}
    public void draw(Graphics g){
        g.drawImage(soundIMG[0][0],x,y,width,height,null);
    }

}
