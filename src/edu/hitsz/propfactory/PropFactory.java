package edu.hitsz.propfactory;

import edu.hitsz.aircraft.AbstractAircraft;

public interface PropFactory {
    public AbstractAircraft creatprop(int locationX, int locationY, int speedX, int speedY, int hp);
}
