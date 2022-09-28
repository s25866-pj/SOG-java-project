package main;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 120;
    public Game(){
        gamePanel=new GamePanel();
        gameWindow=new GameWindow(gamePanel);
        gamePanel.requestFocus();
        StartGameLoop();
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
                updateGame();
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

    private void updateGame() {
        gamePanel.updateGame();
    }
}
