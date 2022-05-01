package edu.hitsz.aircraft;

import edu.hitsz.application.gui.Main;

/**
 * 精英敌机
 * 可射击
 *
 * @author hdl
 */


public class ElitEnemy extends AbstractAircraft{

    public ElitEnemy(double locationX, double locationY, double speedX, double speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }
    /**攻击方式 */


    @Override
    public void forward() {
        super.forward();
        // 判定 y 轴向下飞行出界
        if (locationY >= Main.WINDOW_HEIGHT ) {
            vanish();
        }
    }

}

