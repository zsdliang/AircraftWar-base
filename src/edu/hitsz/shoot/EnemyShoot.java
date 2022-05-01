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
        AbstractBullet abstractBullet;
        for(int i=0; i<abstractAircraft.getShootNum(); i++){
            // 子弹发射位置相对飞机位置向前偏移
            // 多个子弹横向分散
            abstractBullet = new EnemyBullet(x + (i*3 - abstractAircraft.getShootNum() + 1)*10, y, speedX, speedY, abstractAircraft.getPower());
            res.add(abstractBullet);
        }
        return res;
    }

}
