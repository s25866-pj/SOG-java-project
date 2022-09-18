package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;


public class GamePanel extends JPanel {

    private float XDelta=100,YDelta=100;
    private float speed = 0.5f;
    private float XDir=speed, YDir= speed;
    private int width=100,height=100;
    private MouseInputs mouseInputs;
    private ArrayList<BufferedImage>img=new ArrayList<BufferedImage>();
    private ArrayList<InputStream> is=new ArrayList<InputStream>();
    private String patch="/Captain Clown Nose/Captain Clown Nose with Sword/09-Idle Sword/";
    private int aniTick=0, aniIndex=0, aniSpeed=15;



    public GamePanel(){
        mouseInputs=new MouseInputs(this);
        importIMG();

        addKeyListener(new KeyboardInputs(this));
        setBackground(Color.MAGENTA);
        setPanelSize();
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importIMG() {
        File counter = new File(patch+"Idle Sword 00.png");
        System.out.println(counter);

        for(int i=0;i<=4;i++){
            is.add(getClass().getResourceAsStream(patch+"Idle Sword 0"+i+".png"));
            System.out.println(is.get(i));
        }
        for(int i=0;i<=4;i++){
            try {
                img.add(ImageIO.read(is.get(i)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size=new Dimension(1280,800);
        setMinimumSize(size);
        setPreferredSize(size);
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
        updateAnimationTick();

        g.drawImage(img.get(aniIndex),0,0,128,80,null);



    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex>=img.size()){
                aniIndex=0;
            }
        }
    }

    private void updateRectangle() {
        XDelta+=XDir;
        if(XDelta>300||XDelta<0){
            XDir*=-1;
        }
        YDelta+=YDir;
        if(YDelta>300||YDelta<0){
            YDir*=-1;
        }
    }




}
