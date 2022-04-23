package edu.hitsz.propfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.prop.PropBullet;
/**
 *@author:hdl
 */
public class PropBulletFactory implements PropFactory{

    @Override
    public AbstractAircraft creatprop(double locationX, double locationY, double speedX, double speedY, int hp) {
        return new PropBullet(locationX,locationY,speedX,speedY,hp);
    }
}
