package edu.hitsz.propfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.prop.Prop_Blood;

public class Prop_BloodFactory implements PropFactory{

    @Override
    public AbstractAircraft creatprop(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new Prop_Blood(locationX,locationY,speedX,speedY,hp);
    }
}
