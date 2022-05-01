package edu.hitsz.prop;


import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.gui.Main;

/**
 *@author:hdl
 */
public class PropBomb extends AbstractAircraft {
    public PropBomb(double locationX, double locationY, double speedX, double speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);

        System.out.println("bomb created");
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
