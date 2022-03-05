package stickman.model;
import stickman.model.Entity;

public class Cloud implements Entity {

    Level level;
    double cloudVelocity;
    double cloudXPos = 550;
    double cloudYPos = 56;
    double stickmanXPos;

    public Cloud(Level level, double cloudVelocity) {
        this.level = level;
        this.cloudVelocity = cloudVelocity;
        stickmanXPos = level.getHeroX();
    }

    public Cloud(Level level, double cloudVelocity, double cloudXPos, double cloudYPos) {
        this.level = level;
        this.cloudVelocity = cloudVelocity;
        this.cloudXPos = cloudXPos;
        this.cloudYPos = cloudYPos;
        stickmanXPos = level.getHeroX();
    }

    @Override
    public String getImagePath() {
        return "cloud_1.png";
    }

    @Override
    public double getXPos() {
        cloudXPos -= 1 * cloudVelocity;
        if(cloudXPos < 0){
            if(level.getHeroX() > 300){
                cloudXPos = 200 + level.getHeroX();
            }else{
                cloudXPos = 600;
            }
        }
        return cloudXPos;
    }

    @Override
    public double getYPos() {
        return cloudYPos;
    }

    @Override
    public double getHeight() {
        return 20;
    }

    @Override
    public double getWidth() {
        return 40;
    }

    @Override
    public Layer getLayer() {
        return Entity.Layer.BACKGROUND;
    }

}
