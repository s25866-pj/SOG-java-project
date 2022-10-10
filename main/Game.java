package main;

import game_states.GameStates;
import game_states.Menu;
import game_states.Playing;

import java.awt.*;

import static game_states.GameStates.state;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Playing playing;
    private Menu menu;


    public final static int TILES_DEFAULT_SIZE=32;
    public final static float SCALE=2f;
    public final static int TILES_IN_WIDTH=26;
    public final static int TILES_IN_HEIGHT=14;
    public final static int TILES_SIZE= (int) (TILES_DEFAULT_SIZE*SCALE);
    public final static int GAME_WIDTH=TILES_SIZE*TILES_IN_WIDTH;
    public final static int GAME_HEIGHT=TILES_SIZE*TILES_IN_HEIGHT;




    public Game(){
        initClasses();
        gamePanel=new GamePanel(this);

        gameWindow=new GameWindow(gamePanel);
        gamePanel.requestFocus();


        StartGameLoop();//zawsze na końcu
    }

    private void initClasses() {
        menu=new Menu(this);
        playing=new Playing(this);
    }

    private void StartGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double timePerFrame = 1000000000.0/FPS_SET;
        double timePerUpdate = 1000000000.0/UPS_SET;

        long previousTime = System.nanoTime();

        int frame=0;
        int updates = 0;

        long lastCheck=System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;


        while(true){
//            now=System.nanoTime();
            long currentTime=System.nanoTime();

            deltaU+=((currentTime-previousTime)/timePerUpdate);
            deltaF+=((currentTime-previousTime)/timePerFrame);
            previousTime=currentTime;

            if(deltaU>=1){
                update();
                updates++;
                deltaU--;
            }

            if(deltaF>=1){
                gamePanel.repaint();
                deltaF--;
                frame++;
            }

            if(System.currentTimeMillis()-lastCheck>=1000){
                lastCheck=System.currentTimeMillis();
                System.out.println("FPS: "+frame+"| UPS:"+updates);
                updates=0;
                frame=0;
            }
        }
    }

    private void update() {

        switch (state){

            case PLAYING -> {
                playing.update();
//                player.update();
//                levelManager.update();
            }
            case MENU -> {
                menu.update();
                //menu.update();
            }
        }
    }
    public void render(Graphics g){
        switch (state){

            case PLAYING -> {
                playing.draw(g);
//                levelManager.draw(g);
//                player.render(g);
            }
            case MENU -> {
                menu.draw(g);
                //menu.update();
            }
        }

    }
//     public Player getPlayer(){
//        return player;
// }
//
    public void windowFocusLost() {
        if(state==GameStates.PLAYING){
            playing.getPlayer().resetDirBoolean();
        }
    }
    public Menu getMenu(){
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}
