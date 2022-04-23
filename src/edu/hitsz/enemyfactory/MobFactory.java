package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.MobEnemy;
/**
 *@author:hdl
 */
public class MobFactory implements EnemyFactory{
    @Override
    public AbstractAircraft creatEnemy(double locationX, double locationY, double speedX, double speedY, int hp) {
        return new MobEnemy(locationX,locationY,speedX,speedY,hp);
    }
}
