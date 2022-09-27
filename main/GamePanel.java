package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.Constants.PlayerConstants.*;
import static utilz.Constants.Directions.*;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;


public class GamePanel extends JPanel {

    private float XDelta=100,YDelta=100;
    private float speed = 1f;
    private float XDir=speed, YDir= speed;
    //private int width=100,height=100;
    private MouseInputs mouseInputs;
    private ArrayList<ArrayList<BufferedImage>>img=new ArrayList<>();
    private ArrayList<InputStream> is=new ArrayList<InputStream>();
    private int aniTick=0, aniIndex=0, aniSpeed=15;
    private int playerAction=IDLE;
    private int playerDir=-1;
    private boolean moving=false;

    private int size=3;
    
    public GamePanel(){

        mouseInputs=new MouseInputs(this);
        img=LoadIMG.LoadIMGS();

        addKeyListener(new KeyboardInputs(this));
       // setBackground(Color.MAGENTA);
        setPanelSize();
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }


    private void setPanelSize() {
        Dimension size=new Dimension(1280,800);
        setMinimumSize(size);
        setPreferredSize(size);
    }
    public void setDirection(int direction){
         this.playerDir=direction;
         moving=true;
    }
    public void setMoving(boolean moving){
        this.moving=moving;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateAnimationTick();
        setAnimation();
        updatePosition();
        g.drawImage(img.get(playerAction).get(aniIndex),(int)XDelta,(int)YDelta,64*size,40*size,null);
    }

    private void updatePosition() {
        if(moving){
            switch(playerDir){
                case LEFT:
                    XDelta-=speed;
                    break;
                case UP:
                    YDelta-=speed;
                    break;
                case RIGHT:
                    XDelta+=speed;
                    break;
                case DOWN:
                    YDelta+=speed;
                    break;
            }
        }
    }

    private void setAnimation() {

        if(moving) playerAction=RUNNING;
        else playerAction=IDLE;
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex>=GetSpriteAmount(playerAction)){
                aniIndex=0;
            }
        }
    }

    private void updateRectangle() {
        XDelta+=XDir;
        if(XDelta>300||XDelta<0){
            XDir*=-1;
        }
        YDelta+=YDir;
        if(YDelta>300||YDelta<0){
            YDir*=-1;
        }
    }




}
