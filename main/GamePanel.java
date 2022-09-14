package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import java.util.TimerTask;


public class GamePanel extends JPanel {

    private float XDelta=100,YDelta=100;
    private int frame=0;
    private long lastCheck=0;
    private float XDir=0.01f, YDir= 0.01f;
    private int R=0,G=0,B=0;
    Color color=new Color(R,G,B);
    private MouseInputs mouseInputs;
    public GamePanel(){
        mouseInputs=new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value) {
        this.XDelta+=value;
    }
    public void changeYDelta(int value) {
        this.YDelta+=value;
    }
    public void setRectPos(int x, int y){
        this.XDelta=x;
        this.YDelta=y;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateRectangle();
        //g.setColor(Color.BLUE);
        g.setColor(color);//własny kolor
        g.fillRect((int)XDelta,(int)YDelta,100,100);
        frame++;
        if(System.currentTimeMillis()-lastCheck>=1000){
            lastCheck=System.currentTimeMillis();
            System.out.println("FPS: "+frame);
            frame=0;
        }
        repaint();
    }

    private void updateRectangle() {
        XDelta+=XDir;
        if(XDelta>300||XDelta<0){
            XDir*=-1;
            color=changeColor();
        }
        YDelta+=YDir;
        if(YDelta>300||YDelta<0){
            YDir*=-1;
            color=changeColor();
        }
    }

    private Color changeColor() {
        //R= (int) ((Math.random()*(255-0))+0);
        R= (int) ((Math.random()*(255-0))+0);
        G= (int) ((Math.random()*(255-0))+0);
        B= (int) ((Math.random()*(255-0))+0);
//        R= random.nextInt(255);
//        G= random.nextInt(255);
//        B= random.nextInt(255);
        return new Color(R,G,B);
    }


}
