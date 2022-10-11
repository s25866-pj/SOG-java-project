package entities;

import main.Game;
import main.LoadIMG;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;

public class Player extends Entity {
    private ArrayList<ArrayList<BufferedImage>>img;
    private static boolean moving=false,attacking=false;
    private final float playerSpeed = 1.0f*Game.SCALE;
    private int playerAction=IDLE;
    private float size=Game.SCALE+0.5f;
    private int aniTick=0, aniIndex=0, aniSpeed=15;
    private boolean left,up,down,right,jump;
    private int [][] lvlData;
    private float xDrawOffset=22*Game.SCALE;
    private float yDrawOffset=4*Game.SCALE;

    private float airSpeed=0f;
    private float gravity=0.04f*Game.SCALE;
    private float jumpSpeed =-2.25f*Game.SCALE;
    private float fallSpeedAfterColision=0.5f*Game.SCALE;
private boolean inAir=false;

    public Player( float x, float y,int width, int height) {
        super(x, y,width,height);
        loadAnimations();
        initHitbox(x,y,(int)(20*Game.SCALE),(int)(27*Game.SCALE));
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
        if(jump){
            jump();
        }
        if(!left &&!right && !inAir){return;}
        float xSpeed=0;
        //float ySpeed=0;
        if(left){
            xSpeed-= playerSpeed;
        }
        if (right){
            xSpeed+= playerSpeed;
        }
        if(!inAir){
            if(!IsEntityOnFloor(hitbox,lvlData)){

                inAir=true;
            }
        }
        if(inAir){
            if(CanMoveHere(hitbox.x, hitbox.y+airSpeed, hitbox.width, hitbox.height, lvlData)){
                hitbox.y+=airSpeed;
                airSpeed+=gravity;
                updateXPos(xSpeed);
            }else{
                 hitbox.y=GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                 if(airSpeed>0){
                     resetInAir();
                 }else{
                     airSpeed=fallSpeedAfterColision;
                 }
                 updateXPos(xSpeed);
            }
        }else{
            updateXPos(xSpeed);
        }
        moving=true;
    }



    private void jump() {
        if(inAir){
            return;
        }
        inAir=true;
        airSpeed= jumpSpeed;
    }

    private void resetInAir() {
        inAir=false;
        airSpeed=0;
    }


    private void updateXPos(float xSpeed) {
        if(CanMoveHere(hitbox.x+xSpeed,hitbox.y,hitbox.width,hitbox.height,lvlData)){
            hitbox.x+=xSpeed;
        }
        else {
            hitbox.x=GetEnitityXPoseNextToWall(hitbox, xSpeed);
        }
    }




    private void setAnimation() {
        int startAni=playerAction;
        if(moving) playerAction=RUNNING;
        else playerAction=IDLE;
        if(inAir){
            if(airSpeed<0){
                playerAction=JUMPING;
            }
            else {
                playerAction=FALLING;
            }
        }
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
        if(!IsEntityOnFloor(hitbox,lvlData)){
            inAir=true;
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

    public void setJump(boolean jump) {
        this.jump=jump;

    }
}
