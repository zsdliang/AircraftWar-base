package edu.hitsz.prop;


import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.AbstractBullet;

import java.util.LinkedList;
import java.util.List;

public class Prop_Blood extends AbstractAircraft {
    public Prop_Blood(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);

        System.out.println("blood created");

    }
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

    public List<AbstractBullet> shoot() {
        return new LinkedList<>();
    }
}
