package edu.hitsz.application;

import edu.hitsz.aircraft.HeroAircraft;

public class StrengthenFire implements Runnable{
    private int shootNum;
    private HeroAircraft heroAircraft;
    public StrengthenFire(HeroAircraft heroAircraft){
        this.heroAircraft = heroAircraft;
    }

    @Override
    public void run() {
        shootNum = 7;
        heroAircraft.setShootNum(shootNum);
        long time1 = System.currentTimeMillis();
        long time2 = System.currentTimeMillis();;
        while (time2-time1 < 5000) {
            time2 = System.currentTimeMillis();
            heroAircraft.setShootNum(shootNum);
        }
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        shootNum = 1;
        heroAircraft.setShootNum(shootNum);
    }
}
