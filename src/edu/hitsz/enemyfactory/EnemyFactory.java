package edu.hitsz.enemyfactory;

import edu.hitsz.aircraft.AbstractAircraft;

/**
 *@author:hdl
 */

public interface EnemyFactory {
    /**
     *抽象制造敌机的工厂
     */
    public AbstractAircraft creatEnemy(
            /**
             *locationX
             */
            double locationX,
            /**
             *locationY
             */
            double locationY,
            /**
             *speedX
             */
            double speedX,
            /**
             *speedyY
             */
            double speedY,
            /**
             *hp
             */
            int hp);
}
