package stickman.model;

public class SmartEnemy extends Enemy implements Entity {

    int count = 0;
    int xPointCount = 0;
    double enemyMaxXPos;
    double enemyMinXPos;
    double aboveG;

    public SmartEnemy(Level level, double floorHeight, double enemyXPos, double aboveG){
        super(level, floorHeight, enemyXPos, aboveG);
        this.enemyMaxXPos = enemyXPos + 50;
        this.enemyMinXPos = enemyXPos - 50;
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

    boolean isRight = false;
    @Override
    public double getXPos() {
        if(!markedDead) {
            if (!isRight) {
                if (enemyXPos > enemyMinXPos) {
                    enemyXPos -= 0.25;
                } else if (enemyXPos == enemyMinXPos) {
                    if (xPointCount < 100) {
                        xPointCount++;
                    } else {
                        xPointCount = 0;
                        isRight = true;
                    }
                }
            } else {
                if (enemyXPos < enemyMaxXPos) {
                    enemyXPos += 0.25;
                } else if (enemyXPos == enemyMaxXPos) {
                    if (xPointCount < 100) {
                        xPointCount++;
                    } else {
                        xPointCount = 0;
                        isRight = false;
                    }
                }
            }
        }
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
