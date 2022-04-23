package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.Boss;

/**
 *@author:hdl
 */
public class BossFactory implements EnemyFactory{
    @Override
    public AbstractAircraft creatEnemy(double locationX, double locationY, double speedX, double speedY, int hp) {
        return new Boss(locationX,locationY,speedX,speedY,hp);
    }
}
