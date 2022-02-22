package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public class randomProjectile {
    App app;
    PImage projectileImage;

    private int Xpos,Ypos,X,Y;
    private int SpeedUp = 1;
    private boolean isAlive=true;
    private Invader invader;

    public randomProjectile(Invader invader,PApplet app){
        this.app = (App)app;
        this.invader = invader;
        this.Xpos = this.invader.getXpos()+this.invader.getWidth()/2;
        this.Ypos = this.invader.getYpos()+this.invader.getHeight();
        this.X = this.invader.getXpos()+this.invader.getWidth()/2;
        this.Y = this.invader.getYpos()+this.invader.getHeight();
        this.projectileImage = this.app.loadImage("src/main/resources/projectile.png");
    }
    public void draw(){
        this.app.image(this.projectileImage,this.Xpos,this.Ypos,1,3);
        move();
    }
    public void move(){
        int newY = this.Ypos + SpeedUp;
        if(newY+getHeight()<480){
            this.Ypos = newY;
        }
        else{
            this.Ypos = this.Y;
        }
        setPos(this.Xpos,this.Ypos);
    }
    public boolean isAlive() { return isAlive; }
    public int getXpos() { return Xpos; }
    public int getYpos() { return Ypos; }
    public int getWidth() { return this.projectileImage.width; }
    public int getHeight() { return this.projectileImage.height; }

    public void setPos(int Xpos, int Ypos){
        this.Xpos = Xpos;
        this.Ypos = Ypos;
    }

    public static boolean check_tankIsCollsion(randomProjectile p, Tank k){
        boolean tankisCollison = false;
        if(p.getXpos()<(k.getXpos()+k.getWidth())&&(p.getXpos()+p.getWidth())>k.getXpos()){
            if(p.getYpos()<(k.getYpos()+k.getHeight())&&(p.getHeight()+p.getYpos())>k.getYpos()){
                tankisCollison = true;
            }
        }
        return tankisCollison;
    }
    public static boolean check_barrierIsCollsion(randomProjectile p,Barrier b){
        boolean barrierisCollison = false;
        if(p.getXpos()<(b.getXpos()+b.getWidth())&&(p.getXpos()+p.getWidth())>b.getXpos()){
            if(p.getYpos()<(b.getYpos()+b.getHeight())&&(p.getHeight()+p.getYpos())>b.getYpos()){
                barrierisCollison = true;
            }
        }
        return barrierisCollison;
    }
}
