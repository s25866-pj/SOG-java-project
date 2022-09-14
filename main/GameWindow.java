package main;

import javax.swing.*;

public class GameWindow {
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel){
        jframe= new JFrame("SPO v0.001");
        jframe.setSize(400,400);

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLocationRelativeTo(null);
        jframe.add(gamePanel);
        jframe.setVisible(true);
    }
}
