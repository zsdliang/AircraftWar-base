package edu.hitsz.shoot;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.bullet.AbstractBullet;
import edu.hitsz.bullet.HeroBullet;

import java.util.LinkedList;
import java.util.List;

public class SprayShoot implements ShootStrategy{
    private int direction = -1;
    @Override
    public List<AbstractBullet> shoot(AbstractAircraft abstractAircraft) {
        List<AbstractBullet> res = new LinkedList<>();
        int shootnum = abstractAircraft.getShootNum();
        double x = abstractAircraft.getLocationX();
        double y = abstractAircraft.getLocationY() + direction*2;
        double speedX = 0;
        double speedY = 0;
        int speed = 7;
        AbstractBullet abstractBullet;
        if(shootnum % 2 == 1) {
            for (int i = 0; i < shootnum; i++) {
                if (i % 2 == 1) {
                    speedX += 1.5;
                }
                speedY = direction * Math.pow(speed * speed - speedX * speedX, 0.5);
                abstractBullet = new HeroBullet(x,
                        y,
                        Math.pow(-1, i) * speedX,
                        speedY,
                        abstractAircraft.getPower());
                res.add(abstractBullet);
            }
        }
        else {
            speedX = 0.75;
            for (int i = 0; i < shootnum; i++) {
                if (i % 2 == 0 && i > 1) {
                    speedX += 1.5;
                }
                speedY = direction * Math.pow(speed * speed - speedX * speedX, 0.5);
                abstractBullet = new HeroBullet(x,
                        y,
                        Math.pow(-1, i) * speedX,
                        speedY,
                        abstractAircraft.getPower());
                res.add(abstractBullet);
            }
        }
        return res;
    }
}
