package entities;

import main.Game;
import main.LoadIMG;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.PlayerConstants.*;

public class Player extends Entity {
    private ArrayList<ArrayList<BufferedImage>>img;
    private static boolean moving=false,attacking=false;
    private final float playerSpeed = 2f;
    private int playerAction=IDLE;
    private float size=Game.SCALE+0.5f;
    private int aniTick=0, aniIndex=0, aniSpeed=15;
    private boolean left,up,down,right;

    public Player(int x1, int i, float x, float y) {
        super(x, y);
        loadAnimations();
    }
    public void update(){

        updatePosition();
        updateAnimationTick();
        setAnimation();
    }
    public void render(Graphics g){
        g.drawImage(img.get(playerAction).get(aniIndex),(int)x,(int)y, (int)(64*size),(int)(40*size),null);

    }
    private void loadAnimations(){
        img=LoadIMG.LoadIMGS();
    }


    private void updatePosition() {
        moving=false;
        if(left && !right){
            x-= playerSpeed;
            moving=true;
        }
        else if (right && !left){
            x+= playerSpeed;
            moving=true;
        }
        if(up && !down){
            y-= playerSpeed;
            moving=true;
        }
        else if(down && !up){
            y+= playerSpeed;
            moving=true;
        }

    }


    private void setAnimation() {
        int startAni=playerAction;
        if(moving) playerAction=RUNNING;
        else playerAction=IDLE;
        if(attacking){
            playerAction=ATTACK_1;
        }
        if(startAni!=playerAction){
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick=0;
        aniIndex=0;
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex>=GetSpriteAmount(playerAction)){
                aniIndex=0;
                attacking=false;
            }
        }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void resetDirBoolean() {
        left=false;
        right=false;
        up=false;
        down=false;
    }
    public void setAttacking(boolean attacking){
        this.attacking=attacking;
    }
}
