package invadem;

import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Gift {
    List<PImage> imgs;
    int x;
    int y;
    int width;
    int height;
    int type;
    int hp = 1;
    boolean isAlive;
    Gift(int x, int y) {
        this.imgs = new ArrayList<>();
        this.x = x;
        this.y = y;
        Random rand = new Random();
        int rand_int = rand.nextInt(2);
        type = rand_int;
        this.imgs.add(Pimgs.GIFT_IMG0);
        this.imgs.add(Pimgs.GIFT_IMG1);
        this.width = 16;
        this.height = 16;
    }

    void moveDown() {
       this.y += 1;
    }

    public int getType() {
        return type;
    }

    public boolean setHit() {
        hp--;
        isAlive = hp > 0;
        return isAlive;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void draw(App app){
        if (isAlive()) {
            app.image(this.imgs.get(type), x, y, width,height);
            moveDown();
        }
    }

    public int getXpos() {
        return x;
    }

    public int getYpos() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}