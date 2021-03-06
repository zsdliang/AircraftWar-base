package edu.hitsz.propfactory;

import edu.hitsz.aircraft.AbstractAircraft;
/**
 *@author:hdl
 */
public interface PropFactory {
    /**
     *抽象创造道具
     */
    public AbstractAircraft creatprop(
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
