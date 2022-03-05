package stickman.model;

import stickman.model.Entity;
import java.util.List;

public interface Level {

    List<Entity> getEntities();
    double getHeight();
    double getWidth();

    void tick();

    double getFloorHeight();
    double getHeroX();

    boolean jump();
    boolean moveLeft();
    boolean moveRight();
    boolean stopMoving();
}
