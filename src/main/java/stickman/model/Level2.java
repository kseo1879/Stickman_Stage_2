package stickman.model;

import java.util.ArrayList;
import java.util.List;


public class Level2 implements Level{

    List<Entity> entityList = new ArrayList<>();
    List<Entity> otherEntity = new ArrayList<>();
    List<Enemy> enemyEntity = new ArrayList<>();
    List<Enemy> deadEnemy = new ArrayList<>();

    GameEngine model;
    private String stickmanSize;
    private double stickmanPose;
    private double cloudVelocity;
    private double floorHeight;
    private boolean stickmanRightTick;
    private boolean stickmanLeftTick;
    private boolean stopMovingTick;
    private boolean jumpTick;
    private boolean stickmanRight;
    private boolean stickmanLeft;
    private boolean finished;
    Stickman stickman;
    Cloud cloud1;
    Cloud cloud2;
    Grass grass1;
    Grass grass2;
    Grass grass3;
    Enemy enemy;
    Enemy enemy2;
    SmartEnemy enemy3;
    Finish finish;



    public Level2(String stickmanSize, double stickmanPose, double cloudVelocity, double floorHeight, GameEngine model) {
        this.stickmanSize = stickmanSize;
        this.stickmanPose = stickmanPose;
        this.cloudVelocity = cloudVelocity;
        this.floorHeight = floorHeight;
        this.model = model;
        stickman = new Stickman(this, stickmanSize, stickmanPose, floorHeight);
        addEntities(stickman);
        cloud1 = new Cloud(this, cloudVelocity);
        addEntities(cloud1);
        cloud2 = new Cloud(this, cloudVelocity, 300, 15);
        addEntities(cloud2);
        grass1 = new Grass(this, floorHeight, 300, 0);
        addEntities(grass1);
        addOtherEntities(grass1);
        grass2 = new Grass(this, floorHeight, 370, 30);
        addEntities(grass2);
        addOtherEntities(grass2);
        grass3 = new Grass(this, floorHeight, 430, 60);
        addEntities(grass3);
        addOtherEntities(grass3);
        enemy = new Enemy(this, floorHeight, 700, 0);
        addEntities(enemy);
        enemyEntity.add(enemy);
        enemy2 = new Enemy(this, floorHeight, 900, 0);
        addEntities(enemy2);
        enemyEntity.add(enemy2);
        enemy3 = new SmartEnemy(this, floorHeight, 1000, 0);
        addEntities(enemy3);
        enemyEntity.add(enemy3);
        finish = new Finish(floorHeight, 1500);
        addEntities(finish);

    }


    public void addEntities(Entity entity){
        entityList.add(entity);
    }

    public void addOtherEntities(Entity entity){
        otherEntity.add(entity);
    }

    @Override
    public List<Entity> getEntities() {
        return entityList;
    }

    @Override
    public double getHeight() {
        return 0;
    }

    @Override
    public double getWidth() {
        return 0;
    }

    public void finishCheck(){
        if(stickman.getXPos() > finish.getXPos() + finish.getWidth()){
            finished = true;
        }
    }

    public void enemyCheck(Enemy enemy){
        if((stickman.getXPos() + stickman.getWidth() >= enemy.getXPos())
                && (stickman.getXPos() <= enemy.getXPos() + enemy.getWidth())){
            if(stickman.getYPos() + stickman.getHeight()  == enemy.getYPos()){
                enemy.markedDead = true;

            }else if((stickman.getYPos() + stickman.getHeight() > enemy.getYPos() + 1)
                    && (stickman.getYPos() < enemy.getYPos()+enemy.getHeight() - 1)){
                stickman.markedDead = true;
            }

        }
    }

    public void entityCheck(Entity obstacles){
        if ((stickman.getXPos() + stickman.getWidth() >= obstacles.getXPos())
                && (stickman.getXPos() <= obstacles.getXPos() + obstacles.getWidth())){
            if((stickman.getYPos() + stickman.getHeight() > obstacles.getYPos())
                    && (stickman.getYPos()  < obstacles.getYPos()+obstacles.getHeight())) {
                if (stickman.getXPos() + stickman.getWidth() == obstacles.getXPos()) {
                    if (stickmanRight) {
                        stickmanRightTick = false;
                    }
                }else if(stickman.getXPos() == obstacles.getXPos() + obstacles.getWidth()){
                    if(stickmanLeft){
                        stickmanLeftTick = false;
                    }
                }
            }else if(stickman.getYPos() + stickman.getHeight() < obstacles.getYPos()){
                if(stickmanRight){
                    stickmanRightTick = true;
                }else if(stickmanLeft){
                    stickmanLeftTick = true;
                }
            }else if((stickman.getYPos() > obstacles.getYPos() + obstacles.getHeight())){
                if(stickmanRight){
                    stickmanRightTick = true;
                }else if(stickmanLeft){
                    stickmanLeftTick = true;
                }
                stickman.underEntity = true;
            }
        }
        if ((stickman.getXPos() + stickman.getWidth() > obstacles.getXPos())
                && (stickman.getXPos() < obstacles.getXPos() + obstacles.getWidth())){
            if(stickman.stickmanYPose + stickman.getHeight() == obstacles.getYPos()){
                stickman.onEntity = true;
                stickman.entityHeight = floorHeight - obstacles.getYPos();
                stickman.entitySizeL = obstacles.getHeight();
            }
        }
    }


    int tickTime = 0;
    int finishedCount = 0;
    @Override
    public void tick() {
        finishCheck();
        if(!finished){
            tickTime ++;

            for(Entity obstacles: otherEntity){
                entityCheck(obstacles);
            }

            for(int i = 0 ; i < enemyEntity.size(); i++){
                enemyCheck(enemyEntity.get(i));
                if(enemyEntity.get(i).markedDead){
                    deadEnemy.add(enemyEntity.get(i));
                    enemyEntity.remove(i);
                }
            }

            if(stickman.markedDead){
                for(Enemy dead :deadEnemy){
                    dead.markedDead = false;
                    dead.enemyYPos = floorHeight - dead.getHeight() - dead.aboveG;
                    enemyEntity.add(dead);
                }
                stickman.stickmanXPose = stickmanPose;
                stickman.stickmanYPose = floorHeight - stickman.stickmanHeight;
                stickman.stickmanLife -=1;
                System.out.println("Your Life count is: " + stickman.stickmanLife);
                stickman.markedDead = false;
            }

            if(stopMovingTick){
                stickman.stopMoving();
            }
            else if(stickmanRightTick){
                stickman.setRight(true);
            }else if(stickmanLeftTick){
                stickman.setLeft(true);
            }
            stickman.updateYPos();
        }else if(finished && finishedCount == 0){
            int seconds = (17 * tickTime) / 1000;
            System.out.println("Game Finished");
            System.out.println("You took " + seconds + " seconds to finish this level");
            finishedCount ++;
        }

    }

    @Override
    public double getFloorHeight() {
        return floorHeight;
    }

    @Override
    public double getHeroX() {
        return stickman.getXPos();
    }


    public boolean jump() {
        boolean groundTouch;
        boolean entityTouch;
        jumpTick = true;
        stickman.jump();
        entityTouch = stickman.entityTouch;
        groundTouch = stickman.getGroundTouch();
        return groundTouch || entityTouch;
    }

    @Override
    public boolean moveLeft() {
        stopMovingTick = false;
        stickmanLeft = true;
        stickmanRight = false;
        stickmanLeftTick = true;
        return false;
    }

    @Override
    public boolean moveRight() {
        stopMovingTick = false;
        stickmanRight = true;
        stickmanLeft = false;
        stickmanRightTick = true;
        return false;
    }

    @Override
    public boolean stopMoving() {
        stickmanRight = false;
        stickmanLeft = false;
        stickmanRightTick = false;
        stickmanLeftTick = false;
        stopMovingTick = true;
        return false;
    }
}
