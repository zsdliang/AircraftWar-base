package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.EnemyBullet;

import java.util.LinkedList;
import java.util.List;

public class EnemyShoot implements ShootStrategy{
    private int direction = 1;
    @Override
    public List<AbstractBullet> shoot(AbstractAircraft abstractAircraft) {
        List<AbstractBullet> res = new LinkedList<>();
        double x = abstractAircraft.getLocationX();
        double y = abstractAircraft.getLocationY() + direction*2;
        double speedX = 0;
        double speedY = abstractAircraft.getSpeedY() + direction*7;
        AbstractBullet abstractBullet = new EnemyBullet(x, y, speedX, speedY,20);
        res.add(abstractBullet);
        return res;
    }

}
