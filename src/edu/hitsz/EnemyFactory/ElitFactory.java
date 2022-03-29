package edu.hitsz.EnemyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.ElitEnemy;

public class ElitFactory implements EnemyFactory{
    @Override
    public AbstractAircraft creatEnemy(int locationX, int locationY, int speedX, int speedY, int hp) {
        return new ElitEnemy(locationX,locationY,speedX,speedY,hp);
    }
}
