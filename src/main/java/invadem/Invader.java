package invadem;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Invader {
    private List<PImage> invaderImages = new ArrayList<>();
    private PImage drawImg;
    public static final String ORDINARY = "ordinary";
    public static final String POWER = "power";
    public static final String ARMOURED = "armoured";
    public int frameCounter = 0;
    public int frameUpdate = 30;
    public String invaderType;
    int hp;

    private int Xpos,Ypos,width,height;

    private int speedX=1;
    private int speedY=1;
    private int invader_LeftBoundary = 100;
    private int invader_RightBoundary = 160;
    private int stepX=30;
    private int stepY=8;

    private static final int WALK_LEFT = 1;
    private static final int WALK_RIGHT = 2;
    private static final int WALK_LEFT_DOWN = 3;
    private static final int WALK_RIGHT_DOWN = 4;
    private static final int WALK_H = 30;
    private static final int WALK_V = 8;

    private int currentWalkDerection = WALK_LEFT;
    private int currentSteps = 0;

    public boolean isAlive = true;

    public Invader(int Xpos, int Ypos){
        this.Xpos = Xpos;//invader1Image
        this.Ypos = Ypos;
        this.width = 16;
        this.height = 16;
        this.invaderType = ORDINARY;
        this.invaderImages.add(Pimgs.INVADER_IMG0);
        this.invaderImages.add(Pimgs.INVADER_IMG1);
        this.drawImg = Pimgs.INVADER_IMG0;
    }

    public Invader(int Xpos, int Ypos, String invaderType){
        this.Xpos = Xpos;//invader1Image
        this.Ypos = Ypos;
        this.width = 16;
        this.height = 16;
        this.invaderType = invaderType;
        if (invaderType.equals(ORDINARY)) {
            this.invaderImages.add(Pimgs.INVADER_IMG0);
            this.invaderImages.add(Pimgs.INVADER_IMG1);
            this.drawImg = Pimgs.INVADER_IMG0;
            hp = 1;
        }
        else if (invaderType.equals(POWER)) {
            this.invaderImages.add(Pimgs.INVADER_POWER_IMG0);
            this.invaderImages.add(Pimgs.INVADER_POWER_IMG1);
            this.drawImg = Pimgs.INVADER_POWER_IMG0;
            hp = 1;
        }
        else if (invaderType.equals(ARMOURED)) {
            this.invaderImages.add(Pimgs.INVADER_ARMOURED_IMG0);
            this.invaderImages.add(Pimgs.INVADER_ARMOURED_IMG1);
            this.drawImg = Pimgs.INVADER_ARMOURED_IMG0;
            hp = 3;
        }
    }

    public int getXpos(){
        return this.Xpos;
    }//invader1Image
    public int getYpos(){
        return this.Ypos;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public void setPos(int Xpos, int Ypos){
        this.Xpos = Xpos;//invader1Image
        this.Ypos = Ypos;
    }

    public void walk1step(App a) {
        if (a.tick % 2 == 0) {
            switch (currentWalkDerection) {
                case WALK_LEFT:
                    if (currentSteps < WALK_H) {
                        Xpos -= 1;
                    }
                    else {
                        currentWalkDerection = WALK_LEFT_DOWN;
                        Ypos += 1;
                        drawImg = invaderImages.get(1);
                        currentSteps = 0;
                    }
                    break;
                case WALK_RIGHT:;
                    if (currentSteps < WALK_H) {
                        Xpos += 1;
                    }
                    else {
                        currentWalkDerection = WALK_RIGHT_DOWN;
                        Ypos += 1;
                        drawImg = invaderImages.get(1);
                        currentSteps = 0;
                    }
                    break;
                case WALK_LEFT_DOWN:
                    if (currentSteps < WALK_V) {
                        Ypos += 1;
                    }
                    else {
                        currentWalkDerection = WALK_RIGHT;
                        Xpos += 1;
                        drawImg = invaderImages.get(0);
                        currentSteps = 0;
                    }
                    break;
                case WALK_RIGHT_DOWN:
                    if (currentSteps < WALK_V) {
                        Ypos += 1;
                    }
                    else {
                        currentWalkDerection = WALK_LEFT;
                        Xpos -= 1;
                        drawImg = invaderImages.get(0);
                        currentSteps = 0;
                    }
                    break;
            }
            currentSteps++;
        }
    }

    public void draw(App app){
        if(this.isAlive){
            app.image(drawImg, Xpos, Ypos, width,height);
        }
    }

    public boolean setHit() {
        hp--;
        isAlive = hp > 0;
        return isAlive;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public boolean invaderShot(App a) {
        Projectile p;
        if (invaderType.equals(POWER))
            p = new Projectile(Xpos, Ypos, true);
        else
            p = new Projectile(Xpos, Ypos, false);
        p.setSpeedUp(1);
        a.getInvaderShoots().add(p);
        return true;
    }
}
