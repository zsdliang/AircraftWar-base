package edu.hitsz.aircraft;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeroAircraftTest {
    private HeroAircraft hero;

    @BeforeEach
    void enter(){
        System.out.println("--------starting the method test--------");
    }

    @AfterEach
    void quit(){
        hero = null;
        System.out.println("--------test finished---------");
    }

//    @Test
//    void shoot() {
//        System.out.println("--------starting to test the shoot() method--------");
//        hero = HeroAircraft.getInstance();
//
//        for(int i =1;i<4;i++) {
//            hero.setShootNum(i);
//            List<AbstractBullet> heroBullets = hero.shoot();
//            System.out.println("shootNum:"+hero.getShootNum());
//            for(AbstractBullet bullet :heroBullets){
//                System.out.println("X:"+bullet.getLocationX());
//                System.out.println("Y:"+bullet.getLocationY());
//                System.out.println("speedY:"+bullet.getSpeedY());
//            }
//        }
//    }
    @Test
    void getInstance() {
        System.out.println("--------starting to test the getInstance() method--------");
        HeroAircraft hero1;
        HeroAircraft hero2;
        hero1 = HeroAircraft.getInstance();
        System.out.println("hero1 shootNum:"+hero1.getShootNum());
        hero1.setShootNum(10086);
        System.out.println("hero1 shootNum:"+hero1.getShootNum());
        hero2 = HeroAircraft.getInstance();
        System.out.println("hero2 shootNum:"+hero2.getShootNum());
    }

}