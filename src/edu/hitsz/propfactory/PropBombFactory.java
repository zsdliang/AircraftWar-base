package edu.hitsz.propfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.prop.PropBomb;
/**
 *@author:hdl
 */
public class PropBombFactory implements PropFactory{

    @Override
    public AbstractAircraft creatprop(double locationX, double locationY, double speedX, double speedY, int hp) {
        return new PropBomb(locationX,locationY,speedX,speedY,hp);
    }
}
