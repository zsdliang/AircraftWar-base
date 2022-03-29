package edu.hitsz.EnemyFactory;

import edu.hitsz.aircraft.AbstractAircraft;

public interface EnemyFactory {
    public AbstractAircraft creatEnemy(int locationX, int locationY, int speedX, int speedY, int hp);
}
