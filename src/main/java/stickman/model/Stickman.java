package stickman.model;

public class Stickman implements Entity {

    Level level;
    String stickmanSize;
    double floorHeight;
    double stickmanXPose;
    double stickmanYPose;
    double stickmanHeight;
    double stickmanWidth;
    double jumpHeight;
    double entityHeight = 0;
    double entitySizeL = 0;
    double prevEntityHeight = 0;
    int stickmanLife = 99;
    boolean left = false;
    boolean right = false;
    boolean jump = false;
    boolean groundTouch = true;
    boolean entityTouch = false;
    boolean onEntity = false;
    boolean underEntity = false;
    boolean markedDead = false;

    public Stickman(Level level, String stickmanSize, double stickmanPose, double floorHeight) {
        this.level = level;
        this.stickmanSize = stickmanSize;
        this.stickmanXPose = stickmanPose;
        this.floorHeight = floorHeight;
        setSize(this.stickmanSize);
    }

    public void setSize(String stickmanSize){

        if(stickmanSize.equalsIgnoreCase("tiny")){
            this.stickmanHeight = 16;
            this.stickmanWidth =  8;
            this.jumpHeight = 30;
        }else if(stickmanSize.equalsIgnoreCase("normal")){
            this.stickmanHeight = 50;
            this.stickmanWidth =  24;
            this.jumpHeight = 100;
        }else if(stickmanSize.equalsIgnoreCase("large")){
            this.stickmanHeight = 80;
            this.stickmanWidth =  40;
            this.jumpHeight = 160;
        }else if(stickmanSize.equalsIgnoreCase("giant")){
            this.stickmanHeight = 120;
            this.stickmanWidth =  60;
            this.jumpHeight = 240;
        }
        this.stickmanYPose = floorHeight - stickmanHeight;
    }

    public void jump() {
        jump = true;
    }

    public void updateYPos(){
        if(prevEntityHeight == entityHeight){
            prevEntityHeight = entityHeight;
        }
        if(onEntity){
            if(jump && groundTouch && !entityTouch){
                if(stickmanYPose > floorHeight - entityHeight + entitySizeL && stickmanYPose > floorHeight - stickmanHeight - jumpHeight){
                    stickmanYPose -= 2;
                    if(stickmanYPose == floorHeight - entityHeight + entitySizeL || stickmanYPose == floorHeight- stickmanHeight - jumpHeight){
                        stickmanYPose +=2;
                        groundTouch = false;
                    }
                }else{
                    if(stickmanYPose > floorHeight - stickmanHeight - jumpHeight){
                        stickmanYPose -= 2;
                    }else if (stickmanYPose == floorHeight - stickmanHeight - jumpHeight){
                        stickmanYPose += 2;
                        groundTouch = false;
                    }
                }
            }
            else if(jump && !groundTouch && entityTouch){
                if(prevEntityHeight == entityHeight){
                    if(stickmanYPose > floorHeight - stickmanHeight - jumpHeight - entityHeight){
                        stickmanYPose -= 2;
                    }else if (stickmanYPose == floorHeight - stickmanHeight - jumpHeight - entityHeight){
                        stickmanYPose += 2;
                        entityTouch = false;
                    }
                }else if(prevEntityHeight != entityHeight){
                    if(stickmanYPose > floorHeight - stickmanHeight - jumpHeight - prevEntityHeight){
                        stickmanYPose -= 2;
                    }else if (stickmanYPose == floorHeight - stickmanHeight - jumpHeight - prevEntityHeight){
                        stickmanYPose += 2;
                        entityTouch = false;
                    }
                }
            }

            else if(!jump && !groundTouch && entityTouch){
                if(stickmanYPose < floorHeight - stickmanHeight - entityHeight){
                    stickmanYPose +=2;
                }else if(stickmanYPose == floorHeight - stickmanHeight - entityHeight){
                    groundTouch = false;
                    entityTouch = true;
                }
            }

            else if(jump && !groundTouch && !entityTouch){
                if(stickmanYPose < floorHeight - stickmanHeight - entityHeight){
                    stickmanYPose += 2;
                }else if(stickmanYPose  == floorHeight - stickmanHeight - entityHeight){
                    prevEntityHeight = entityHeight;
                    entityTouch = true;
                    jump = false;
                }
            }
            onEntity = false;
        }else if(!onEntity){
            if(jump && groundTouch && !entityTouch){
                if(stickmanYPose > floorHeight - stickmanHeight - jumpHeight){
                    stickmanYPose -= 2;
                }else if (stickmanYPose == floorHeight - stickmanHeight - jumpHeight){
                    stickmanYPose += 2;
                    groundTouch = false;
                }
            }
            else if(jump && !groundTouch && entityTouch){
                if(stickmanYPose > floorHeight - stickmanHeight - jumpHeight - entityHeight){
                    stickmanYPose -= 2;
                }else if (stickmanYPose == floorHeight - stickmanHeight - jumpHeight - entityHeight){
                    stickmanYPose += 2;
                    entityTouch = false;
                }
            }
            else if(jump && !groundTouch && !entityTouch){
                if(stickmanYPose < floorHeight - stickmanHeight){
                    stickmanYPose +=2;
                }else if(stickmanYPose  == floorHeight - stickmanHeight ){
                    groundTouch = true;
                    entityTouch = false;
                    jump = false;
                }
            }else if(!jump && !groundTouch && entityTouch){
                if(stickmanYPose < floorHeight - stickmanHeight){
                    stickmanYPose +=2;
                }else if(stickmanYPose == floorHeight - stickmanHeight){
                    groundTouch = true;
                    entityTouch = false;
                }
            }
        }
    }

{}    public boolean getGroundTouch(){
        return groundTouch;
    }

//    @Override
    public void setLeft(boolean b) {
        left = b;
        isRight = false;
        right = false;
        stickmanXPose -=1;
    }

//    @Override
    public void setRight(boolean b) {
        right = b;
        isRight = true;
        left = false;
        stickmanXPose +=1;
    }

//    @Override
    public void stopMoving() {
        left = false;
        right = false;
    }

    int count = 0;
    int standCount = 0;
    boolean isRight = true;

    @Override
    public String getImagePath() {
        if(right){
            if(count == 0){
                isRight = true;
                count++;
                return "ch_walk1.png";
            }
            else if(count == 1){
                isRight = true;
                count++;
                return "ch_walk1.png";
            }
            else if(count == 2){
                isRight = true;
                count++;
                return "ch_walk3.png";
            }
            else if(count == 3){
                isRight = true;
                count = 0;
                return "ch_walk3.png";
            }
        }else if(left){
            if(count == 0){
                isRight = false;
                count++;
                return "ch_walk5.png";
            }
            else if(count == 1){
                isRight = false;
                count++;
                return "ch_walk6.png";
            }
            else if(count == 2){
                isRight = false;
                count++;
                return "ch_walk7.png";
            }
            else if(count == 3){
                isRight = false;
                count = 0;
                return "ch_walk8.png";
            }
        }else if(!right && !left){
            if(isRight){
                if(standCount == 0){
                    standCount ++;
                    return "ch_stand1.png";
                }
                else if(standCount == 1){
                    standCount ++;
                    return "ch_stand1.png";
                }
                else{
                    standCount = 0;
                    return "ch_stand2.png";
                }
            }else if(!isRight){
                if(standCount == 0){
                    standCount ++;
                    return "ch_stand4.png";
                }
                else if(standCount == 1){
                    standCount ++;
                    return "ch_stand4.png";
                }
                else{
                    standCount = 0;
                    return "ch_stand5.png";
                }
            }
        }
        return "";
       // return "Seulgi.png";
    }

    @Override
    public double getXPos() {
        if(stickmanXPose < 0){
            stickmanXPose = 0;
        }
        return stickmanXPose;
    }


    @Override
    public double getYPos() {

        return stickmanYPose + 10;
    }

    @Override
    public double getHeight() {
        return stickmanHeight;
    }

    @Override
    public double getWidth() {
        return stickmanWidth;
    }

    @Override
    public Layer getLayer() {
        return Entity.Layer.FOREGROUND;
    }


}
