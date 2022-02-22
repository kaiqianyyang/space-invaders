package invadem;

import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class App extends PApplet {
    public int tick = 0;

    public Integer currentHighScore = 10000;
    public Integer currentScore = 0;
    int level = 0;

    PImage emptyImage;
    PImage nextlevelImage;
    PImage gameoverImage;

    Barrier Barrier1_Left;
    Barrier Barrier1_Top;
    Barrier Barrier1_Right;
    Barrier Barrier1_Solid1;
    Barrier Barrier1_Solid2;
    Barrier Barrier1_Solid3;
    Barrier Barrier1_Solid4;

    Barrier Barrier2_Left;
    Barrier Barrier2_Top;
    Barrier Barrier2_Right;
    Barrier Barrier2_Solid1;
    Barrier Barrier2_Solid2;
    Barrier Barrier2_Solid3;
    Barrier Barrier2_Solid4;
    Barrier Barrier3_Left;
    Barrier Barrier3_Top;
    Barrier Barrier3_Right;
    Barrier Barrier3_Solid1;
    Barrier Barrier3_Solid2;
    Barrier Barrier3_Solid3;
    Barrier Barrier3_Solid4;

    List<Barrier> barriers;
    List<Gift> gifts;

    boolean doubleShotFlag = false;

    public final int BLACK = this.color(0,0,0);
    public final int WHITE = this.color(255, 255, 255);
    public int INVADER_SHOT_PERIDO = 300;
    public final int DEADLINE_Y = 410;

    private Tank tank;
    private Invader randomInvader;
    private int barrier1LeftHitTime = 0;

    private ArrayList<Invader> invaders = new  ArrayList<Invader>();
    private int INVADER_SPACEX = 30;
    private int INVADER_SPACEY = 30;
    private int INVADER_LEFTSPACE = 180;//LEFT_BOUNDRY
    private int INVADER_RIGHTSPACE = 80;

    private ArrayList<Projectile> tankShoots = new  ArrayList<Projectile>();
    private ArrayList<Projectile> invaderShoots = new  ArrayList<Projectile>();

    private boolean gameoverFlag = false;
    private boolean nextlevelFlag = false;

    //PROJECTILE
    public ArrayList<Projectile> gettankShoots() {
        return this.tankShoots;
    }
    
    public void setProjectile(ArrayList<Projectile> tankShoots) {
        this.tankShoots = tankShoots;
    }

    public ArrayList<Projectile> getInvaderShoots() { return invaderShoots; }

    //在游戏开始时创建所有的新对象
    private void createStartObjects() {
        tankShoots = new  ArrayList<Projectile>();
        invaderShoots = new  ArrayList<Projectile>();
        invaders = new ArrayList<>();
        gifts = new ArrayList<>();
        this.tank = new Tank(Pimgs.TANK_IMG);

        this.emptyImage = Pimgs.BARRIER_EMPTY_IMG;
        this.gameoverImage = Pimgs.GAMEOVER_IMG;
        this.nextlevelImage = Pimgs.NEXTLEVEL_IMG;

        int invaderX = INVADER_LEFTSPACE;
        int invaderY = INVADER_RIGHTSPACE;

        for(int i=0;i<40;i++){
            String invaderType;
            switch (i / 10) {
                case 0: invaderType = Invader.ARMOURED; break;
                case 1: invaderType = Invader.POWER;    break;
                default: invaderType = Invader.ORDINARY; break;
            }

            Invader invader = new Invader(invaderX, invaderY, invaderType);
            invaders.add(invader);
            invaderX += INVADER_SPACEX;
            if(i==9||i==19||i==29){
                invaderX = INVADER_LEFTSPACE;
                invaderY += INVADER_SPACEY;
            }
        }

        this.Barrier1_Left = new Barrier(Pimgs.BARRIER_LEFT_IMG0,200,420,    BarrierType.LEFT );
        this.Barrier1_Top = new Barrier(Pimgs.BARRIER_TOP_IMG0,208,420,      BarrierType.TOP  );
        this.Barrier1_Right = new Barrier(Pimgs.BARRIER_RIGHT_IMG0,216,420,  BarrierType.RIGHT);
        this.Barrier1_Solid1 = new Barrier(Pimgs.BARRIER_SOLID_IMG0,200,428, BarrierType.SOLID);
        this.Barrier1_Solid2 = new Barrier(Pimgs.BARRIER_SOLID_IMG0,216,428, BarrierType.SOLID);
        this.Barrier1_Solid3 = new Barrier(Pimgs.BARRIER_SOLID_IMG0,200,436, BarrierType.SOLID);
        this.Barrier1_Solid4 = new Barrier(Pimgs.BARRIER_SOLID_IMG0,216,436, BarrierType.SOLID);

        this.Barrier2_Left = new Barrier(Pimgs.BARRIER_LEFT_IMG0,308,420,   BarrierType.LEFT );//barrier left Xpos=308
        this.Barrier2_Top = new Barrier(Pimgs.BARRIER_TOP_IMG0,316,420,     BarrierType.TOP  );//barrier space = 108
        this.Barrier2_Right = new Barrier(Pimgs.BARRIER_RIGHT_IMG0,324,420, BarrierType.RIGHT);
        this.Barrier2_Solid1 = new Barrier(Pimgs.BARRIER_SOLID_IMG0,308,428,BarrierType.SOLID);
        this.Barrier2_Solid2 = new Barrier(Pimgs.BARRIER_SOLID_IMG0,324,428,BarrierType.SOLID);
        this.Barrier2_Solid3 = new Barrier(Pimgs.BARRIER_SOLID_IMG0,308,436,BarrierType.SOLID);
        this.Barrier2_Solid4 = new Barrier(Pimgs.BARRIER_SOLID_IMG0,324,436,BarrierType.SOLID);

        this.Barrier3_Left = new Barrier(Pimgs.BARRIER_LEFT_IMG0,424,420,   BarrierType.LEFT );//barrier space=84
        this.Barrier3_Top = new Barrier(Pimgs.BARRIER_TOP_IMG0,432,420,     BarrierType.TOP  );
        this.Barrier3_Right = new Barrier(Pimgs.BARRIER_RIGHT_IMG0,440,420, BarrierType.RIGHT);
        this.Barrier3_Solid1 = new Barrier(Pimgs.BARRIER_SOLID_IMG0,424,428,BarrierType.SOLID);
        this.Barrier3_Solid2 = new Barrier(Pimgs.BARRIER_SOLID_IMG0,440,428,BarrierType.SOLID);
        this.Barrier3_Solid3 = new Barrier(Pimgs.BARRIER_SOLID_IMG0,424,436,BarrierType.SOLID);
        this.Barrier3_Solid4 = new Barrier(Pimgs.BARRIER_SOLID_IMG0,440,436,BarrierType.SOLID);

        Barrier[] barrierArray = {
                Barrier1_Left, Barrier1_Top, Barrier1_Right, Barrier1_Solid1, Barrier1_Solid2, Barrier1_Solid3, Barrier1_Solid4,
                Barrier2_Left, Barrier2_Top, Barrier2_Right, Barrier2_Solid1, Barrier2_Solid2, Barrier2_Solid3, Barrier2_Solid4,
                Barrier3_Left, Barrier3_Top, Barrier3_Right, Barrier3_Solid1, Barrier3_Solid2, Barrier3_Solid3, Barrier3_Solid4};
        barriers = Arrays.asList(barrierArray);
    }

    public App() {
        //Set up your objects
    }

    public void setup() {
        frameRate(60);
        this.background(BLACK);
        tick = 0;
        Pimgs.loadImgs(this);
        textFont(Pimgs.MY_FONT);
        level = 0;
        //textSize(16);

        createStartObjects();
    }

    public void settings() {
        size(640, 480);
    }

    private void checkCollision() {
        //tank shot and invaders
        for(int i=0;i < tankShoots.size();i++){
            for(int j=0;j<invaders.size();j++){
                if(tankShoots.get(i) != null && invaders.get(j).isAlive() && tankShoots.get(i).check_IsCollsion(invaders.get(j))){
                    this.tankShoots.set(i, null);
                    boolean stillAlive = this.invaders.get(j).setHit();
                    if (!stillAlive) {
                        currentScore += invaders.get(j).invaderType.equals(Invader.ORDINARY)? 100: 250;
                        currentHighScore = Math.max(currentScore, currentHighScore);
                    }
                }
            }
        }

        //tank shot and barriers
        for(int i=0;i < tankShoots.size();i++){
            Projectile p = tankShoots.get(i);
            for(int j=0;j<barriers.size();j++){
                if(tankShoots.get(i) != null && barriers.get(j).isAlive() && tankShoots.get(i).check_IsCollsion(barriers.get(j))){
                    this.tankShoots.set(i, null);
                    this.barriers.get(j).setHit();
                }
            }
        }

        //tank shot and barriers
        for(int i=0;i < tankShoots.size();i++){
            Projectile p = tankShoots.get(i);
            for(int j=0;j<gifts.size();j++){
                if(tankShoots.get(i) != null && gifts.get(j) != null && gifts.get(j).isAlive() && tankShoots.get(i).check_IsCollsion(gifts.get(j))){
                    this.tankShoots.set(i, null);
                    if (this.gifts.get(j).getType() == 0) {
                        tank.doubleFlag = false;
                        for (Barrier b: barriers) {
                            b.recover();
                        }
                    }
                    else {
                        tank.doubleFlag = true;
                    }
                    this.gifts.get(j).setHit();
                    //this.gifts.set(j, null);
                }
            }
        }



        //invader shot and barriers
        for(int i=0;i < invaderShoots.size();i++){
            Projectile p = invaderShoots.get(i);
            for(int j=0;j<barriers.size();j++){
                if(invaderShoots.get(i) != null && barriers.get(j).isAlive() && invaderShoots.get(i).check_IsCollsion(barriers.get(j))){
                    this.invaderShoots.set(i, null);
                    this.barriers.get(j).setHit();
                }
            }
        }

        //invader shot and barriers
        for(int i=0;i < invaderShoots.size();i++){
            Projectile p = invaderShoots.get(i);
            if(invaderShoots.get(i) != null && invaderShoots.get(i).check_IsCollsion(tank)){
                boolean tankAlive = tank.setHit(invaderShoots.get(i).isPower);
                this.invaderShoots.set(i, null);
                gameoverFlag = gameoverFlag || !tankAlive;
                if (gameoverFlag)
                    return;
            }
        }

        //invader too close to barrier
        for (int i = 0; i < invaders.size(); i++) {
            if (invaders.get(i).getYpos() >= DEADLINE_Y) {
                gameoverFlag = true;
                return;
            }
        }

        //goto next level
        nextlevelFlag = true;
        for (int i = 0; i < invaders.size(); i++) {
            if (invaders.get(i).isAlive) {
                nextlevelFlag = false;
                break;
            }
        }

    }

    public void draw() {
        this.background(BLACK);

        //textFont(Pimgs.MY_FONT);
        //textSize(16);
        //text("hahah", 0, 0);
        tick++;
        //Main Game Loop
        if (gameoverFlag) {
            createStartObjects();
            this.image(Pimgs.GAMEOVER_IMG, 200, 240, Pimgs.GAMEOVER_IMG.width, Pimgs.GAMEOVER_IMG.height);
            //System.out.println("game over");
            return;
        }

        if (nextlevelFlag) {
            level++;
            INVADER_SHOT_PERIDO -= (INVADER_SHOT_PERIDO <= 60 ? 60: 0);
            createStartObjects();
            this.image(Pimgs.NEXTLEVEL_IMG, 200, 240, Pimgs.NEXTLEVEL_IMG.width, Pimgs.NEXTLEVEL_IMG.height);
            //System.out.println("game over");
            return;
        }

        textSize(12);
        text(currentScore.toString(), 10, 30);
        text(currentHighScore.toString(), 540, 30);
        fill(WHITE);

        if (tick % INVADER_SHOT_PERIDO == 0) {
            Invader invaderShoter = getRandomInvader(invaders);
            invaderShoter.invaderShot(this);
        }

        if (tick % INVADER_SHOT_PERIDO == 32) {
            Random rand = new Random();
            gifts.add(new Gift(rand.nextInt(400) + 110, 0));
        }

        checkCollision();
        //this.background(this.BLACK);

        if (gameoverFlag || nextlevelFlag)
            return;

        this.tank.draw(this);

        for(int i=0;i<this.invaders.size();i++){
            Invader invader = this.invaders.get(i);
            invader.walk1step(this);
            invader.draw(this);
        }

        //tank 子弹
        for(int i=0;i<this.tankShoots.size();i++){
            Projectile p = this.tankShoots.get(i);
            if(p != null) {
                p.draw(this);
            }
        }

        //invader 子弹
        for(int i=0;i<this.invaderShoots.size();i++){
            Projectile p = this.invaderShoots.get(i);
            if(p != null) {
                p.draw(this);
            }
        }

        for (Barrier b: barriers) {
            b.draw(this);
        }

        for (Gift g: gifts) {
            g.draw(this);
        }
    }

    //ARROW KEYS------TANK
    public void keyPressed() {
        gameoverFlag = false;
        nextlevelFlag = false;
        if(key==CODED){
            if(keyCode==LEFT){
                this.tank.isPressed = true;
                this.tank.goLeft();//turn left
            }
            if(keyCode==RIGHT){
                this.tank.isPressed = true;
                this.tank.goRight();//turn right
            }
        }
        //space key to shoot
        if (key == ' ') {
            this.tank.spaceisPressed = true;
            this.tank.shoot(this);
        }
        if(this.tank.getXpos()<110||this.tank.getXpos()>460){ this.tank.isPressed = false; }
    }

    public void keyReleased() {
        if(key==CODED){
            if(keyCode==LEFT||keyCode==RIGHT){
                this.tank.isPressed = false;
            }
        }
        if (key == ' ') {
            this.tank.spaceisPressed = false;
        }
    }

    public Invader getRandomInvader(ArrayList<Invader> invaders) {
        Random rand = new Random();
        Invader inv = null;
        do {
            inv = invaders.get(rand.nextInt(invaders.size()));
        } while (inv == null);
        return inv;
    }

    public static void main(String[] args) {

        PApplet.main("invadem.App");
    }

}
