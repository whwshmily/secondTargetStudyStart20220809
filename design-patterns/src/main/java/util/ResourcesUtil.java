package util;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class ResourcesUtil {
    //    private static final String PREFIX = "TANK_IMAGES";
    private static final String TANK_IMAGES_PLAYER1_UP = "images/GoodTankUp.png";
    private static final String TANK_IMAGES_PLAYER1_LEFT = "images/GoodTankLeft.png";
    private static final String TANK_IMAGES_PLAYER1_RIGHT = "images/GoodTankRight.png";
    private static final String TANK_IMAGES_PLAYER1_DOWN = "images/GoodTankDown.png";
    private static final String TANK_IMAGES_PLAYER2_UP = "images/BadTankUp.png";
    private static final String TANK_IMAGES_PLAYER2_LEFT = "images/BadTankLeft.png";
    private static final String TANK_IMAGES_PLAYER2_RIGHT = "images/BadTankRight.png";
    private static final String TANK_IMAGES_PLAYER2_DOWN = "images/BadTankDown.png";
    private static final String TANK_IMAGES_COMPUTER_UP = "images/tankU.gif";
    private static final String TANK_IMAGES_COMPUTER_LEFT = "images/tankL.gif";
    private static final String TANK_IMAGES_COMPUTER_RIGHT = "images/tankR.gif";
    private static final String TANK_IMAGES_COMPUTER_DOWN = "images/tankD.gif";
    private static final String BULLET_IMAGES_LEFT = "images/bulletL.gif";
    private static final String BULLET_IMAGES_RIGHT = "images/bulletR.gif";
    private static final String BULLET_IMAGES_UP = "images/bulletU.gif";
    private static final String BULLET_IMAGES_DOWN = "images/bulletD.gif";

    private static final HashMap<String, BufferedImage> BUFFER_IMAGE_MAPS = new HashMap<String, BufferedImage>();

    static {
        ClassLoader classLoader = ResourcesUtil.class.getClassLoader();
        try {
            BufferedImage PLAYER1_UP = ImageIO.read(classLoader.getResourceAsStream(TANK_IMAGES_PLAYER1_UP));
            BufferedImage PLAYER1_LEFT = ImageIO.read(classLoader.getResourceAsStream(TANK_IMAGES_PLAYER1_LEFT));
            BufferedImage PLAYER1_RIGHT = ImageIO.read(classLoader.getResourceAsStream(TANK_IMAGES_PLAYER1_RIGHT));
            BufferedImage PLAYER1_DOWN = ImageIO.read(classLoader.getResourceAsStream(TANK_IMAGES_PLAYER1_DOWN));
            BufferedImage PLAYER2_UP = ImageIO.read(classLoader.getResourceAsStream(TANK_IMAGES_PLAYER2_UP));
            BufferedImage PLAYER2_LEFT = ImageIO.read(classLoader.getResourceAsStream(TANK_IMAGES_PLAYER2_LEFT));
            BufferedImage PLAYER2_RIGHT = ImageIO.read(classLoader.getResourceAsStream(TANK_IMAGES_PLAYER2_RIGHT));
            BufferedImage PLAYER2_DOWN = ImageIO.read(classLoader.getResourceAsStream(TANK_IMAGES_PLAYER2_DOWN));
            BufferedImage BULLET_UP = ImageIO.read(classLoader.getResourceAsStream(BULLET_IMAGES_UP));
            BufferedImage BULLET_RIGHT = ImageIO.read(classLoader.getResourceAsStream(BULLET_IMAGES_RIGHT));
            BufferedImage BULLET_DOWN = ImageIO.read(classLoader.getResourceAsStream(BULLET_IMAGES_DOWN));
            BufferedImage BULLET_LEFT = ImageIO.read(classLoader.getResourceAsStream(BULLET_IMAGES_LEFT));
            BufferedImage COMPUTER_UP = ImageIO.read(classLoader.getResourceAsStream(TANK_IMAGES_COMPUTER_UP));
            BufferedImage COMPUTER_LEFT = ImageIO.read(classLoader.getResourceAsStream(TANK_IMAGES_COMPUTER_LEFT));
            BufferedImage COMPUTER_RIGHT = ImageIO.read(classLoader.getResourceAsStream(TANK_IMAGES_COMPUTER_RIGHT));
            BufferedImage COMPUTER_DOWN = ImageIO.read(classLoader.getResourceAsStream(TANK_IMAGES_COMPUTER_DOWN));
            BUFFER_IMAGE_MAPS.put("PLAYER1_UP", PLAYER1_UP);
            BUFFER_IMAGE_MAPS.put("PLAYER1_LEFT", PLAYER1_LEFT);
            BUFFER_IMAGE_MAPS.put("PLAYER1_RIGHT", PLAYER1_RIGHT);
            BUFFER_IMAGE_MAPS.put("PLAYER1_DOWN", PLAYER1_DOWN);
            BUFFER_IMAGE_MAPS.put("PLAYER2_UP", PLAYER2_UP);
            BUFFER_IMAGE_MAPS.put("PLAYER2_LEFT", PLAYER2_LEFT);
            BUFFER_IMAGE_MAPS.put("PLAYER2_RIGHT", PLAYER2_RIGHT);
            BUFFER_IMAGE_MAPS.put("PLAYER2_DOWN", PLAYER2_DOWN);
            BUFFER_IMAGE_MAPS.put("BULLET_UP", BULLET_UP);
            BUFFER_IMAGE_MAPS.put("BULLET_RIGHT", BULLET_RIGHT);
            BUFFER_IMAGE_MAPS.put("BULLET_DOWN", BULLET_DOWN);
            BUFFER_IMAGE_MAPS.put("BULLET_LEFT", BULLET_LEFT);
            BUFFER_IMAGE_MAPS.put("COMPUTER_UP", COMPUTER_UP);
            BUFFER_IMAGE_MAPS.put("COMPUTER_LEFT", COMPUTER_LEFT);
            BUFFER_IMAGE_MAPS.put("COMPUTER_RIGHT", COMPUTER_RIGHT);
            BUFFER_IMAGE_MAPS.put("COMPUTER_DOWN", COMPUTER_DOWN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getDirBufferImage(String name, String dir) {
        String bufferImageName = name.toUpperCase() + "_" + dir.toUpperCase();
        return BUFFER_IMAGE_MAPS.get(bufferImageName);
    }
}
