package stickman.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class GameEngineImpl implements GameEngine{

    public String stickmanSize;
    public double stickmanPose;
    public double cloudVelocity;
    public double levelNum;
    Level level;

    public GameEngineImpl(String s){
        try{
            JSONObject jsonObject = (JSONObject) readJsonSimpleDemo(s);
            this.stickmanSize  =(String) jsonObject.get("stickmanSize");
            JSONObject poseTable = (JSONObject) jsonObject.get("stickmanPos");
            this.stickmanPose = (Double) poseTable.get("x");
            this.cloudVelocity = (Double) jsonObject.get("cloudVelocity");
            this.levelNum = (Double) jsonObject.get("levelNum");
            levelConstructor(levelNum);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void levelConstructor(double desiredLevel){
        int desiredLevelInt = (int)desiredLevel;
        if(desiredLevelInt == 1){
            this.level = new Level1(stickmanSize, stickmanPose, cloudVelocity, 350, this);
        }else if(desiredLevelInt == 2){
            this.level = new Level2(stickmanSize, stickmanPose, cloudVelocity, 350, this);
        }
    }

    public static Object readJsonSimpleDemo(String filename) throws Exception {
        FileReader reader = new FileReader(filename);
        JSONParser jsonParser = new JSONParser();
        return jsonParser.parse(reader);

    }


    @Override
    public Level getCurrentLevel() {
        return level;
    }

    @Override
    public void startLevel() {

    }

    @Override
    public boolean jump() {
        return getCurrentLevel().jump();
    }

    @Override
    public boolean moveLeft() {
        return getCurrentLevel().moveLeft();
    }

    @Override
    public boolean moveRight() {
        return getCurrentLevel().moveRight();
    }

    @Override
    public boolean stopMoving() {
        return getCurrentLevel().stopMoving();
    }

    @Override
    public void tick() {
        getCurrentLevel().tick();
    }
}
