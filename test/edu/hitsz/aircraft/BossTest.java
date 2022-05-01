package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.gui.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BossTest {
    private AbstractAircraft boss;
    @BeforeEach
    void enter(){
        boss = new Boss((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELIT_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                3,
                0,
                500);
        System.out.println("--------starting the method test--------");
    }

    @AfterEach
    void quit(){
        boss = null;
        System.out.println("--------test finished---------");
    }

    @Test
    void decreaseHp() {
        System.out.println("--------start testing the decreaseHp() method--------");
        System.out.println("the hp of the boss is:"+boss.getHp());
        System.out.println(boss.notValid());
        boss.decreaseHp(250);
        System.out.println("now the hp of the boss is"+boss.getHp());
        System.out.println(boss.notValid());
        boss.decreaseHp(500);
        System.out.println("now the hp of the boss is"+boss.getHp());
        System.out.println(boss.notValid());
    }

//    @Test
//    void shoot() {
//        System.out.println("--------starting to test the shoot() method--------");
//        List<AbstractBullet> enemybullets = boss.shoot();
//        for(AbstractBullet bullet :enemybullets){
//            System.out.println("X:"+bullet.getLocationX());
//            System.out.println("Y:"+bullet.getLocationY());
//            System.out.println("speedY:"+bullet.getSpeedY());
//            System.out.println("power:"+bullet.getPower());
//        }
//    }
}