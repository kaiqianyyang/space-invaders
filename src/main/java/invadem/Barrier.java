package invadem;

//import org.checkerframework.checker.units.qual.A;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

enum BarrierType {
    TOP(1), LEFT(2), RIGHT(3), SOLID(4);
    private int i;
    BarrierType(int i) {
        this.i = i;
    }
}

public class Barrier {
    PImage barrierImage;
    List<PImage> imgs;

    private int Xpos,Ypos;
    private int width=8;
    private int height=8;
    private boolean isAlive=true;
    private int hittime=0;
    BarrierType type;
    public Barrier(PImage barrierImage, int Xpos,int Ypos, BarrierType type){
        this.Xpos=Xpos;
        this.Ypos=Ypos;
        this.barrierImage = barrierImage;
        imgs = new ArrayList<>();
        hittime = 0;
        switch (type) {
            case LEFT:
                imgs.add(Pimgs.BARRIER_LEFT_IMG0);
                imgs.add(Pimgs.BARRIER_LEFT_IMG1);
                imgs.add(Pimgs.BARRIER_LEFT_IMG2); break;
            case TOP:
                imgs.add(Pimgs.BARRIER_TOP_IMG0);
                imgs.add(Pimgs.BARRIER_TOP_IMG1);
                imgs.add(Pimgs.BARRIER_TOP_IMG2); break;
            case RIGHT:
                imgs.add(Pimgs.BARRIER_RIGHT_IMG0);
                imgs.add(Pimgs.BARRIER_RIGHT_IMG1);
                imgs.add(Pimgs.BARRIER_RIGHT_IMG2); break;
            case SOLID:
                imgs.add(Pimgs.BARRIER_SOLID_IMG0);
                imgs.add(Pimgs.BARRIER_SOLID_IMG1);
                imgs.add(Pimgs.BARRIER_SOLID_IMG2); break;
        }
        this.type = type;
    }

    public void draw(App app){
        if (isAlive()) {
            app.image(imgs.get(hittime), Xpos, Ypos);
        }
    }

    public void recover() {
        hittime = 0;
    }

    public int getXpos() { return Xpos; }
    public int getYpos(){
        return Ypos;
    }
    public int getWidth(){
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }
    public boolean isAlive() { return hittime < 3; }
    public boolean setHit() {
        hittime++;
        return hittime < 3;
    }
}



