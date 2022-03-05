package stickman.model;

public class Enemy implements Entity {

    int count = 0;
    Level level;
    double enemyXPos;
    double enemyYPos;
    double floorHeight;
    double aboveG;
    boolean markedDead = false;

    public Enemy(Level level, double floorHeight, double enemyXPos, double aboveG){
        this.level = level;
        this.floorHeight = floorHeight;
        this.enemyXPos = enemyXPos;
        this.enemyYPos = floorHeight - getHeight() - aboveG;
        this.aboveG = aboveG;
    }

    @Override
    public String getImagePath() {
        if(count ==0){
            count ++;
            return"slimeBa.png";
        }else if(count ==1){
            count ++;
            return"slimeBb.png";
        }
        else if(count ==2){
            count ++;
            return"slimeGa.png";
        }
        else if(count ==3){
            count ++;
            return"slimeGb.png";
        }
        else if(count ==4){
            count ++;
            return"slimePa.png";
        }
        else if(count ==5){
            count ++;
            return"slimePb.png";
        }
        else if(count ==6){
            count ++;
            return"slimeRa.png";
        }
        else if(count ==7){
            count ++;
            return"slimeRa.png";
        }
        else if(count ==8){
            count ++;
            return"slimeRb.png";
        }
        else if(count ==9){
            count ++;
            return"slimeYa.png";
        }
        else if(count ==10){
            count = 0;
            return"slimeYb.png";
        }
        return "";
    }

    @Override
    public double getXPos() {
        return enemyXPos;
    }

    @Override
    public double getYPos() {
        if(markedDead){
            enemyYPos += 1;
            if(enemyYPos == 1000){
                enemyYPos = 1000;
            }
        }else{
            return floorHeight - getHeight() - aboveG;
        }
        return enemyYPos;
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
