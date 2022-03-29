package edu.hitsz.propfactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.prop.Prop_Bomb;

public class Prop_BombFactory implements PropFactory{

    @Override
    public AbstractAircraft creatprop(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new Prop_Bomb(locationX,locationY,speedX,speedY,hp);
    }
}
