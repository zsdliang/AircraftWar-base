package edu.hitsz.propfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.prop.Prop_Bullet;

public class Prop_BulletFactory implements PropFactory{

    @Override
    public AbstractAircraft creatprop(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new Prop_Bullet(locationX,locationY,speedX,speedY,hp);
    }
}
