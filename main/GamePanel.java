package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class GamePanel extends JPanel {

    private float XDelta=100,YDelta=100;
    private int frame=0;
    private long lastCheck=0;
    private float speed = 0.5f;
    private float xDir=speed, yDir= speed;
    private int R=0,G=0,B=0;
    ArrayList<MyRect> rects= new ArrayList<MyRect>();
    Color color=new Color(R,G,B);
    private int width=100,height=100;
    private MouseInputs mouseInputs;
    public GamePanel(){
        mouseInputs=new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void spawnRect(int x,int y){
        rects.add(new MyRect(x,y));
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
        for(MyRect rect : rects ){
            rect.updateRectangle();
            rect.draw(g);
        }

    }

    public class MyRect {
        int x, y,h,w;
        int xDir=1,yDir=1;
        Color color;

        public MyRect(int x, int y){
            this.x=x;
            this.y=y;
            h= (int) ((Math.random()*(100-0))+1);
            w=h;
            color = newColor();
        }
        private void updateRectangle() {
            this.x+=xDir;
            this.y+=yDir;
            if((x+w)>400||x<0){
                xDir*=-1;
                color=newColor();
            }

            if((y+h)>400||y<0){
                yDir*=-1;
                color=newColor();
            }
        }
        public void draw(Graphics g){
            g.setColor(color);
            g.fillRect(x,y,h,w);
        }
    }
    private Color newColor(){
        return new Color ((int) ((Math.random()*(255-0))+0),(int) ((Math.random()*(255-0))+0),(int) ((Math.random()*(255-0))+0));
    }

//    private void updateRectangle() {
//        XDelta+=xDir;
//        if(XDelta>300||XDelta<0){
//            xDir*=-1;
//            color=newColor();
//        }
//        YDelta+=yDir;
//        if(YDelta>300||YDelta<0){
//            yDir*=-1;
//            color=newColor();
//        }
//    }
//    private Color changeColor() {
//        //R= (int) ((Math.random()*(255-0))+0);
//        R= (int) ((Math.random()*(255-0))+0);
//        G= (int) ((Math.random()*(255-0))+0);
//        B= (int) ((Math.random()*(255-0))+0);
////        R= random.nextInt(255);
////        G= random.nextInt(255);
////        B= random.nextInt(255);
//        return new Color(R,G,B);
//    }


}
