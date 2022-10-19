package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadUI {
    private static final String PATH="/Buttons/PauseButtons/";
    public static final String PAUSE_PATH=PATH+"pause_menu.png";
    public static final String PAUSE_SOUNDS_BUTTONS=PATH+"sound_button.png";
    public static final String PAUSE_URM_BUTTONS=PATH+"urm_buttons.png";

    
    public static BufferedImage GetPauseAtlas(String name){
        InputStream is = LoadIMG.class.getResourceAsStream(name);
        BufferedImage img = null;
        try {
           img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }
}
