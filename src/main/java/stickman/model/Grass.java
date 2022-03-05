package stickman.model;

public class Grass implements Entity {

    Level level;
    double grassXPos;
    double floorHeight;
    double aboveG;

    public Grass(Level level, double floorHeight, double grassXPos, double aboveG){
        this.level = level;
        this.floorHeight = floorHeight;
        this.grassXPos = grassXPos;
        this.aboveG = aboveG;
    }

    @Override
    public String getImagePath() {
        return "foot_tile.png";
    }

    @Override
    public double getXPos() {
        return grassXPos;
    }

    @Override
    public double getYPos() {
        return floorHeight - getHeight() - aboveG;
    }

    @Override
    public double getHeight() {
        return 26;
    }

    @Override
    public double getWidth() {
        return 26;
    }

    @Override
    public Layer getLayer() {
        return Layer.EFFECT;
    }


}
