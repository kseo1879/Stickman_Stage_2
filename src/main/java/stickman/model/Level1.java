package stickman.model;
import java.util.*;


public class Level1 implements Level{

    private String stickmanSize;
    private double stickmanPose;
    private double cloudVelocity;
    private double floorHeight;
    GameEngine model;
    Stickman stickman;
    Cloud cloud;


    public Level1(String stickmanSize, double stickmanPose, double cloudVelocity, double floorHeight, GameEngine model) {
        this.stickmanSize = stickmanSize;
        this.stickmanPose = stickmanPose;
        this.cloudVelocity = cloudVelocity;
        this.floorHeight = floorHeight;
        this.model = model;
        stickman = new Stickman(this, stickmanSize, stickmanPose, floorHeight);
        cloud = new Cloud(this, cloudVelocity);
    }

    @Override
    public List<Entity> getEntities() {
        List<Entity> entityList = new ArrayList<>();
        entityList.add(stickman);
        entityList.add(cloud);
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

    @Override
    public void tick() {

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
        stickman.jump();
        groundTouch = stickman.getGroundTouch();
        return groundTouch;
    }

    @Override
    public boolean moveLeft() {
        stickman.setLeft(true);
        return false;
    }

    @Override
    public boolean moveRight() {
        stickman.setRight(true);
        return false;
    }

    @Override
    public boolean stopMoving() {
        stickman.stopMoving();
        return false;
    }
}
