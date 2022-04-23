package edu.hitsz.propfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.prop.PropBlood;
/**
 *@author:hdl
 */
public class PropBloodFactory implements PropFactory{

    @Override
    public AbstractAircraft creatprop(double locationX, double locationY, double speedX, double speedY, int hp) {
        return new PropBlood(locationX,locationY,speedX,speedY,hp);
    }
}
