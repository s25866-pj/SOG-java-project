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


    public static ArrayList<ArrayList<BufferedImage>> LoadIMGS(){

        for(int i =0;i<MAX_PLAYER_CONSTANTS;i++){
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