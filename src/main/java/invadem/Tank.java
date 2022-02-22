package invadem;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.List;

public class Tank {
    private PImage tankImage;
    public boolean isPressed = false;
    public boolean spaceisPressed = false;

    //tank = loadImage("src/main/resources/tank1.png");
    private int Xpos,Ypos;
    private int tank_LeftBoundary = 180;
    private int tank_RightBoundary = 438;//460-22

    private int speedX = 1;
    private boolean isAlive;
    int hittime = 0;
    boolean doubleFlag = false;
    //private int speedY = 2;

    public Tank(PImage tankImage){
        this.Xpos=309;//(640-22)/2
        this.Ypos=454;//(480-16-10)
        this.tankImage = tankImage;
        isAlive = true;
        doubleFlag = false;
        hittime = 0;
    }

    public void draw(App app) {
        app.image(this.tankImage,this.getXpos(),this.getYpos(),22,16);
        if(isPressed()){
            move();
        }
    }
    public void move(){
        int newX= this.Xpos+this.speedX;
        this.Xpos = newX;
    }

    public boolean isPressed() {
        return isPressed;
    }
    public boolean spaceisPressed() { return spaceisPressed; }

    //tank position
    public int getXpos() {
        return this.Xpos;
    }
    public int getYpos() {
        return this.Ypos;
    }
    public int getWidth(){
        return this.tankImage.width;
    }
    public int getHeight(){
        return this.tankImage.height;
    }

    //arrow key
    public void goLeft(){
        this.speedX = -Math.abs(speedX);
        int newX= this.Xpos+speedX;
        if(newX>=tank_LeftBoundary) this.Xpos = newX;
    }

    public void goRight(){
        this.speedX = Math.abs(speedX);
        int newX= this.Xpos+speedX;
        if(newX<=tank_RightBoundary) this.Xpos = newX;
    }

    public void shoot(App app) {
        if (doubleFlag) {
            //System.out.println("aa");
            doubleShoot(app);
        }
        else {
            //System.out.println("bb");
            singleShoot(app);
        }
    }

    public void singleShoot(App app) {
        int x = this.Xpos + (this.getWidth()/2);
        int y = this.Ypos;
        Projectile p = new Projectile(x,y);
        List<Projectile> tankshoots = app.gettankShoots();
        for (int i = 0; i < tankshoots.size(); i++) {
            if (tankshoots.get(i) == null) {
                tankshoots.set(i, p);
                return;
            }
        }
        tankshoots.add(p);
    }

    public void doubleShoot(App app) {
        int x1 = this.Xpos + (this.getWidth());
        int x2 = this.Xpos;
        int y = this.Ypos;

        Projectile p1 = new Projectile(x1,y);
        Projectile p2 = new Projectile(x2,y);

        List<Projectile> tankshoots = app.gettankShoots();
        tankshoots.add(p1);
        tankshoots.add(p2);
    }

    public boolean setHit() {
        hittime++;
        if (hittime == 3) {
            isAlive = false;
        }
        return isAlive;
    }

    public boolean setHit(boolean isPowerProjectile) {
        if (isPowerProjectile) {
            hittime = 3;
        }
        else {
            hittime++;
        }

        if (hittime == 3) {
            isAlive = false;
        }
        return isAlive;
    }
}
