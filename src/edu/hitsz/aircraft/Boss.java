package edu.hitsz.aircraft;

import edu.hitsz.application.Main;

/**
 *@author hdl
 */
public class Boss extends AbstractAircraft{
    public Boss(double locationX, double locationY, double speedX, double speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }


    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

}
