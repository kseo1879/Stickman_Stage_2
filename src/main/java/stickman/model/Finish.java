package stickman.model;

public class Finish implements Entity {

    double floorHeight;
    double finishXPos;

    public Finish(double floorHeight, double finishXPos){
        this.floorHeight = floorHeight;
        this.finishXPos = finishXPos;
    }

    @Override
    public String getImagePath() {
        return "Racing-Flag.png";
    }

    @Override
    public double getXPos() {
        return finishXPos;
    }

    @Override
    public double getYPos() {
        return floorHeight - getHeight();
    }

    @Override
    public double getHeight() {
        return 40;
    }

    @Override
    public double getWidth() {
        return 64;
    }

    @Override
    public Layer getLayer() {
        return Layer.EFFECT;
    }
}
