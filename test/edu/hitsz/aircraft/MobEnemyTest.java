package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;
import edu.hitsz.bullet.HeroBullet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MobEnemyTest {
    private AbstractAircraft mob;
    @BeforeEach
    void enter(){
        mob = new Boss((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELIT_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                0,
                10,
                20);
        System.out.println("--------starting the method test--------");
    }

    @AfterEach
    void quit(){
        mob = null;
        System.out.println("--------test finished---------");
    }
    @Test
    void forward() {
        for(int i=0;i<100;i++) {
            System.out.print(mob.notValid() );
            System.out.print(" X:"+mob.getLocationX());
            System.out.print(" Y:"+mob.getLocationY());
            System.out.print("\n");
            mob.forward();
        }
    }

    @Test
    void crash() {
        HeroBullet bullet  = new HeroBullet(mob.getLocationX(),mob.getLocationY(),0,1,20) ;
        System.out.println(mob.crash(bullet));
    }
}