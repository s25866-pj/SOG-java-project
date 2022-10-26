package game_states;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PauseOverlay;
import utilz.LoadIMG;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

import static main.Game.GAME_WIDTH;
import static main.Game.SCALE;
import static utilz.Constants.Environment.*;

public class Playing extends State implements StateMethods {
    private Player player;
    private LevelManager levelManager;
    private PauseOverlay pauseOverlay;
    private boolean paused = false;
    private int xLvlOffset;
    private int leftBorder= (int) (0.2* GAME_WIDTH);
    private int rightBorder= (int) (0.8* GAME_WIDTH);
    private int lvlTilesWide= LoadIMG.GetLevelData()[0].length;
    private int maxTilesOffset=lvlTilesWide-Game.TILES_IN_WIDTH;
    private int maxLvlOffset=maxTilesOffset* Game.TILES_SIZE;
    public BufferedImage backgroundIMG,bigCloud,smallCloud;
    private int[] smallCloudsPosX,smallCloudsPosY;
    Random random=new Random();
    public Playing(Game game){
        super(game);
        initClasses();
        backgroundIMG=LoadIMG.GetSpriteAtlas(LoadIMG.PLAYING_BACKGROUND_IMG);
        bigCloud=LoadIMG.GetSpriteAtlas(LoadIMG.PLAYING_BACKGROUND_BIG_CLOUD);
        smallCloud=LoadIMG.GetSpriteAtlas(LoadIMG.PLAYING_BACKGROUND_SMALL_CLOUD);
        smallCloudsPosX=new int[8];
        smallCloudsPosY=new int[8];
        generateSmallCloudsPosX();
        generateSmallCloudsPosY();
    }

    private void generateSmallCloudsPosY() {
        for (int i = 0; i < smallCloudsPosY.length; i++) {
            smallCloudsPosY[i]= random.nextInt(6)+1;
        }
    }

    private void generateSmallCloudsPosX() {
        for (int i = 0; i < smallCloudsPosX.length; i++) {
            smallCloudsPosX[i]= (int) (90*SCALE) + random.nextInt((int) (100* SCALE));
        }
    }

    public void unpauseGame(){
        paused=false;
    }
    private void initClasses() {
        levelManager=new LevelManager(game);
        player = new Player(200,200,(int)(64*SCALE),(int)(40*SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
        pauseOverlay=new PauseOverlay(this);
    }
    public Player getPlayer(){
        return player;
    }

    public void windowFocusLost() {
        player.resetDirBoolean();
    }

    @Override
    public void update() {
        if(!paused){
            levelManager.update();
            player.update();
            checkColseToBorder();
        }else{
            pauseOverlay.update();
        }
    }

    private void checkColseToBorder() {
        int playerX= (int) player.getHitbox().x;
        int diff =playerX-xLvlOffset;
        if(diff>rightBorder){
            xLvlOffset+=diff-rightBorder;
        }else if(diff<leftBorder){
            xLvlOffset+=diff-leftBorder;
        }

        if(xLvlOffset>maxLvlOffset){
            xLvlOffset=maxLvlOffset;
        }else if(xLvlOffset<0){
            xLvlOffset=0;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundIMG,0,0, GAME_WIDTH,Game.GAME_HEIGHT,null);
        drawClouds(g);
        levelManager.draw(g,xLvlOffset);
        player.render(g,xLvlOffset);
        if (paused){
            g.setColor(new Color(0,0,0,150));
            g.fillRect(0,0, GAME_WIDTH,Game.GAME_HEIGHT);
            pauseOverlay.draw(g);
        }
    }

    private void drawClouds(Graphics g) {

        for (int i = 0; i <3 ; i++) {
            g.drawImage(bigCloud,BIG_CLOUD_WIDTH*i-(int)(xLvlOffset*0.3),(int)(204* SCALE),BIG_CLOUD_WIDTH,BIG_CLOUD_HEIGHT,null);
        }
        for (int i = 0; i <smallCloudsPosX.length ; i++) {

           //zajebiście rzucające się chmury g.drawImage(smallCloud,0+SMALL_CLOUD_WIDTH*i+ (random.nextInt(50)+10),(int)((100+random.nextInt(10)+5)* SCALE),SMALL_CLOUD_WIDTH,SMALL_CLOUD_HEIGHT,null);
            g.drawImage(smallCloud,SMALL_CLOUD_WIDTH*smallCloudsPosY[i]*i-(int)(xLvlOffset*0.6),smallCloudsPosX[i],SMALL_CLOUD_WIDTH,SMALL_CLOUD_HEIGHT,null);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused){
            pauseOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused){
            pauseOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused){
            pauseOverlay.mouseMoved(e);
        }
    }

    @Override
    public void KeyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_W:
                player.setUp(true);
                break;
            case KeyEvent.VK_S:
                player.setDown(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_E:
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
           case KeyEvent.VK_ESCAPE:
               paused=!paused;
                break;
        }
    }

    @Override
    public void KeyRelease(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_W:
                player.setUp(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }

    public void mouseDragged(MouseEvent e) {
        if(paused){
            pauseOverlay.mouseDragged(e);
        }
    }
}
