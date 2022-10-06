package entities;

import main.Game;
import main.LoadIMG;
import utilz.HelpMethods;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.CanMoveHere;

public class Player extends Entity {
    private ArrayList<ArrayList<BufferedImage>>img;
    private static boolean moving=false,attacking=false;
    private final float playerSpeed = 2f;
    private int playerAction=IDLE;
    private float size=Game.SCALE+0.5f;
    private int aniTick=0, aniIndex=0, aniSpeed=15;
    private boolean left,up,down,right;
    private int [][] lvlData;
    private float xDrawOffset=22*Game.SCALE;
    private float yDrawOffset=4*Game.SCALE;

    public Player( float x, float y,int width, int height) {
        super(x, y,width,height);
        loadAnimations();
        initHitbox(x,y,20*Game.SCALE,26*Game.SCALE);
    }
    public void update(){

        updatePosition();
        updateAnimationTick();
        setAnimation();
    }
    public void render(Graphics g){
        g.drawImage(img.get(playerAction).get(aniIndex),(int) (hitbox.x-xDrawOffset),(int) (hitbox.y-yDrawOffset), width,height,null);
       // drawHitbox(g);
    }
    private void loadAnimations(){
        img=LoadIMG.LoadIMGS();
    }


    private void updatePosition() {
        moving=false;
        if(!left &&!right &&!up &&!down){return;}
        float xSpeed=0,ySpeed=0;
        if(left && !right){
            xSpeed= -playerSpeed;
        }
        else if (right && !left){
            xSpeed= playerSpeed;
        }
        if(up && !down ){
            ySpeed= -playerSpeed;
        }
        else if(down && !up){
            ySpeed= playerSpeed;
        }
//        if(CanMoveHere(x+xSpeed,y+ySpeed,width,height,lvlData)){
//            this.x+=xSpeed;
//            this.y+=ySpeed;
//            moving=true;
//        }
        if(CanMoveHere(hitbox.x+xSpeed,hitbox.y+ySpeed,hitbox.width,hitbox.height,lvlData)){
            hitbox.x+=xSpeed;
            hitbox.y+=ySpeed;
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
    public void loadLvlData(int[][] lvlData){
        this.lvlData=lvlData;
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
