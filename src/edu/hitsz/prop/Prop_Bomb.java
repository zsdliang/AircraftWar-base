package edu.hitsz.prop;


import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.AbstractBullet;

import java.util.LinkedList;
import java.util.List;

public class Prop_Bomb extends AbstractAircraft {
    public Prop_Bomb(int locationX, int locationY, int speedX, int speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);

        System.out.println("bomb created");
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
