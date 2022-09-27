package main;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static utilz.Constants.PlayerConstants.*;

public class LoadIMG {
static ArrayList<ArrayList<BufferedImage>> Animation = new ArrayList<>();
static ArrayList<ArrayList<InputStream>> is= new ArrayList<>();
static ArrayList<InputStream> temp=new ArrayList<>();
static ArrayList<BufferedImage> tempImg=new ArrayList<>();
private static int playerAction=IDLE;
private static BufferedImage TempImg;
private static BufferedImage ErrIMG;

    static {
        try {
            ErrIMG = ImageIO.read(LoadIMG.class.getResourceAsStream("/Captain Clown Nose/Captain Clown Nose with Sword/ERROR_PIC/ERR_PIC 01.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<ArrayList<BufferedImage>> LoadIMGS(){

        for(int i =0;i<8;i++){
            for(int j=0;j<GetSpriteAmount(i);j++){

                temp.add(LoadIMG.class.getResourceAsStream(GetPathName(i)+(j+1)+".png"));
            }

            is.add((ArrayList<InputStream>) temp.clone());
            temp.clear();
        }
        for(int i=0;i< is.size();i++){
            for(int j=0;j<is.get(i).size();j++){
                try{
                    tempImg.add(ImageIO.read(is.get(i).get(j)));
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            Animation.add((ArrayList<BufferedImage>) tempImg.clone());
            tempImg.clear();
        }
        return Animation;
    }
}