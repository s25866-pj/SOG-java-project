package main;

public class Game implements Runnable{
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
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
        long now = System.nanoTime();
        long lastFrame=System.nanoTime();
        int frame=0;
        long lastCheck=System.currentTimeMillis();
        while(true){
            now=System.nanoTime();
            if(now - lastFrame>=timePerFrame){
                gamePanel.repaint();
                lastFrame=now;
                frame++;
            }

            if(System.currentTimeMillis()-lastCheck>=1000){
                lastCheck=System.currentTimeMillis();
                System.out.println("FPS: "+frame);
                frame=0;
            }
        }
    }
}
