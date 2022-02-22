package invadem;
import processing.core.PFont;
import processing.core.PImage;

class Pimgs {
    public static PImage TANK_IMG;

    public static PImage INVADER_IMG0;
    public static PImage INVADER_IMG1;

    public static PImage INVADER_POWER_IMG0;
    public static PImage INVADER_POWER_IMG1;

    public static PImage INVADER_ARMOURED_IMG0;
    public static PImage INVADER_ARMOURED_IMG1;

    public static PImage BARRIER_LEFT_IMG0;
    public static PImage BARRIER_LEFT_IMG1;
    public static PImage BARRIER_LEFT_IMG2;

    public static PImage BARRIER_RIGHT_IMG0;
    public static PImage BARRIER_RIGHT_IMG1;
    public static PImage BARRIER_RIGHT_IMG2;

    public static PImage BARRIER_TOP_IMG0;
    public static PImage BARRIER_TOP_IMG1;
    public static PImage BARRIER_TOP_IMG2;

    public static PImage BARRIER_SOLID_IMG0;
    public static PImage BARRIER_SOLID_IMG1;
    public static PImage BARRIER_SOLID_IMG2;

    public static PImage BARRIER_EMPTY_IMG;

    public static PImage PROJECTILE_IMG;
    public static PImage PROJECTILE_LG_IMG;

    public static PImage GIFT_IMG0;
    public static PImage GIFT_IMG1;

    public static PImage GAMEOVER_IMG;

    public static PImage NEXTLEVEL_IMG;

    public static PFont MY_FONT;


    public static final String resourceRoot = "";

    public static void loadImgs(App a) {

        TANK_IMG = a.loadImage(resourceRoot + "tank1.png");

        INVADER_IMG0 = a.loadImage(resourceRoot + "invader1.png");
        INVADER_IMG1 = a.loadImage(resourceRoot + "invader2.png");

        INVADER_POWER_IMG0 = a.loadImage(resourceRoot + "invader1_power.png");
        INVADER_POWER_IMG1 = a.loadImage(resourceRoot + "invader2_power.png");

        INVADER_ARMOURED_IMG0 = a.loadImage(resourceRoot + "invader1_armoured.png");
        INVADER_ARMOURED_IMG1 = a.loadImage(resourceRoot + "invader2_armoured.png");

        BARRIER_LEFT_IMG0 = a.loadImage(resourceRoot + "barrier_left1.png");
        BARRIER_LEFT_IMG1 = a.loadImage(resourceRoot + "barrier_left2.png");
        BARRIER_LEFT_IMG2 = a.loadImage(resourceRoot + "barrier_left3.png");

        BARRIER_RIGHT_IMG0 = a.loadImage(resourceRoot + "barrier_right1.png");
        BARRIER_RIGHT_IMG1 = a.loadImage(resourceRoot + "barrier_right2.png");
        BARRIER_RIGHT_IMG2 = a.loadImage(resourceRoot + "barrier_right3.png");

        BARRIER_TOP_IMG0 = a.loadImage(resourceRoot + "barrier_top1.png");
        BARRIER_TOP_IMG1 = a.loadImage(resourceRoot + "barrier_top2.png");
        BARRIER_TOP_IMG2 = a.loadImage(resourceRoot + "barrier_top3.png");

        BARRIER_SOLID_IMG0 = a.loadImage(resourceRoot + "barrier_solid1.png");
        BARRIER_SOLID_IMG1 = a.loadImage(resourceRoot + "barrier_solid2.png");
        BARRIER_SOLID_IMG2 = a.loadImage(resourceRoot + "barrier_solid3.png");

        BARRIER_EMPTY_IMG = a.loadImage(resourceRoot + "empty.png");

        PROJECTILE_IMG = a.loadImage(resourceRoot + "projectile.png");
        PROJECTILE_LG_IMG = a.loadImage(resourceRoot + "projectile_lg.png");

        GAMEOVER_IMG = a.loadImage(resourceRoot + "gameover.png");

        NEXTLEVEL_IMG = a.loadImage(resourceRoot + "nextlevel.png");

        GIFT_IMG0 = a.loadImage(resourceRoot + "gift1.png");
        GIFT_IMG1 = a.loadImage(resourceRoot + "gift2.png");

        MY_FONT = a.createFont(resourceRoot + "PressStart2P-Regular.ttf", 30);
    }
}