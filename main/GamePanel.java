package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;


public class GamePanel extends JPanel {

    private int XDelta=100;
    private int YDelta=100;
    private MouseInputs mouseInputs;
    public GamePanel(){
        mouseInputs=new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXDelta(int value) {
        this.XDelta+=value;
        repaint();
    }
    public void changeYDelta(int value) {
        this.YDelta+=value;
        repaint();
    }
    public void setRectPos(int x, int y){
        this.XDelta=x;
        this.YDelta=y;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(XDelta,YDelta,100,100);

    }
}
