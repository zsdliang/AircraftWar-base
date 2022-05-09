package edu.hitsz.prop;


import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.application.gui.Main;
import edu.hitsz.bullet.AbstractBullet;

import java.util.List;

/**
 *@author:hdl
 */
public class PropBomb extends AbstractAircraft {
    private List<AbstractAircraft> enemyList;
    private List<AbstractBullet> bulletList;
    private int damage = 60;
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

    public void addEnemy(List<AbstractAircraft> enemyList) {
        this.enemyList = enemyList;
    }
    public void addBullet(List<AbstractBullet> bulletList) {
        this.bulletList = bulletList;
    }

    public void notifyAll(int damage) {
        for(AbstractAircraft enemy: enemyList) {
            enemy.update(damage);
        }
        for(AbstractBullet bullet: bulletList) {
            bullet.update();
        }
    }
    public void action() {
        notifyAll(damage);
    }
}