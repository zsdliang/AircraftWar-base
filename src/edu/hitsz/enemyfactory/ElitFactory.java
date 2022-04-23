package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.ElitEnemy;

/**
 *@author:hdl
 */
public class ElitFactory implements EnemyFactory{
    @Override
    public AbstractAircraft creatEnemy(double locationX, double locationY, double speedX, double speedY, int hp) {
        return new ElitEnemy(locationX,locationY,speedX,speedY,hp);
    }
}
