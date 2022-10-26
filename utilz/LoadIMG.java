package utilz;

import javax.imageio.ImageIO;
import java.awt.*;
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
static BufferedImage tempIMG;
static InputStream tempIS;
//public static final String LVL_1_MAP= "/levels/level_01_data.png";
public static final String LVL_1_MAP= "/levels/level_02_data.png";
public static final String TERRAIN_PATH="/Terrain/basic terrain/Terrain (32x32).png";
public static final String PLAYING_BACKGROUND_IMG="/Terrain/OtherGameIMG/playing_bg_img.png";
public static final String PLAYING_BACKGROUND_BIG_CLOUD="/Terrain/OtherGameIMG/big_clouds.png";
public static final String PLAYING_BACKGROUND_SMALL_CLOUD="/Terrain/OtherGameIMG/small_clouds.png";
public static final String MENU_BUTTONS="/Buttons/MenuButtons/button_atlas.png";
public static final String MENU_BACKGROUND="/Buttons/MenuButtons/menu_background.png";
public static final String MENU_BACKGROUND_IMG="/Buttons/MenuButtons/background_menu.png";

    public static BufferedImage GetSpriteAtlas(String fileName){
            tempIS = LoadIMG.class.getResourceAsStream(fileName);
        try {
            tempIMG =ImageIO.read(tempIS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempIMG;
    }

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

    public static int [][] GetLevelData(){

        BufferedImage img =GetSpriteAtlas(LVL_1_MAP);
        int [][] lvlData = new int[img.getHeight()][img.getWidth()];

        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color= new Color(img.getRGB(i,j));
                int value= color.getRed();
                if (value>=48){
                    value=0;
                }
                lvlData[j][i]= color.getRed();
            }
        }
    return lvlData;
    }
}