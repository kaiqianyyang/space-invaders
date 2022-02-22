package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public class Projectile{
    App app;
    PImage projectileImage;
    boolean isPower;

    private int Xpos,Ypos;
    private int width, height;
    private int SpeedUp = -1;
    private boolean isAlive=true;
    private static int barrier1LeftHitTime=0;

    public Projectile(int Xpos,int Ypos){
        this.Xpos = Xpos;
        this.Ypos = Ypos;
        this.width = 1;
        this.height = 3;
        isPower = false;
        this.projectileImage = Pimgs.PROJECTILE_IMG;
    }

    public Projectile(int Xpos,int Ypos, boolean isPower){
        this.Xpos = Xpos;
        this.Ypos = Ypos;
        this.projectileImage = isPower? Pimgs.PROJECTILE_LG_IMG: Pimgs.PROJECTILE_IMG;
        //this.projectileImage = Pimgs.PROJECTILE_LG_IMG;
        this.width = projectileImage.width;
        this.height = projectileImage.height;
        this.isPower = isPower;
    }

    public void draw(App app){
        app.image(this.projectileImage, this.Xpos, this.Ypos, width, height);
        move();
    }

    public void setSpeedUp(int yspeed) {
        SpeedUp = yspeed;
    }

    public void move(){ this.Ypos = this.Ypos + SpeedUp; }
    public boolean isAlive() { return isAlive; }

    public int getXpos() { return Xpos; }
    public int getYpos() { return Ypos; }
    public int getWidth() { return this.projectileImage.width; }
    public int getHeight() { return this.projectileImage.height; }

    public boolean check_IsCollsion(Invader v){
        boolean isCollison = false;
        if(Xpos <(v.getXpos()+v.getWidth())&&(Xpos+width)>v.getXpos()){
            if(Ypos<(v.getYpos()+v.getHeight())&&(height+Ypos)>v.getYpos()){
                isCollison = true;
            }
        }
        return isCollison;
    }

    public boolean check_IsCollsion(Barrier v){
        boolean isCollison = false;
        if(Xpos <(v.getXpos()+v.getWidth())&&(Xpos+width)>v.getXpos()){
            if(Ypos<(v.getYpos()+v.getHeight())&&(height+Ypos)>v.getYpos()){
                isCollison = true;
            }
        }
        return isCollison;
    }

    public boolean check_IsCollsion(Tank v){
        boolean isCollison = false;
        if(Xpos <(v.getXpos()+v.getWidth())&&(Xpos+width)>v.getXpos()){
            if(Ypos<(v.getYpos()+v.getHeight())&&(height+Ypos)>v.getYpos()){
                isCollison = true;
            }
        }
        return isCollison;
    }

    public boolean check_IsCollsion(Gift v){
        boolean isCollison = false;
        if(Xpos <(v.getXpos()+v.getWidth())&&(Xpos+width)>v.getXpos()){
            if(Ypos<(v.getYpos()+v.getHeight())&&(height+Ypos)>v.getYpos()){
                isCollison = true;
            }
        }
        return isCollison;
    }

    public boolean check_IsCollsion(Projectile v){
        boolean isCollison = false;
        if(Xpos <(v.getXpos()+v.getWidth())&&(Xpos+width)>v.getXpos()){
            if(Ypos<(v.getYpos()+v.getHeight())&&(height+Ypos)>v.getYpos()){
                isCollison = true;
            }
        }
        return isCollison;
    }
}
