package edu.hitsz.application;

import edu.hitsz.aircraft.HeroAircraft;

public class Limit implements Runnable{
    private int shootNum;
    private HeroAircraft heroAircraft;
    private int lastTime = 5000;
    public Limit(HeroAircraft heroAircraft){this.heroAircraft = heroAircraft;}

    @Override
    public void run() {
        heroAircraft.setLimitBreaking(true);
        shootNum = 7;
        heroAircraft.setShootNum(shootNum);
        long time1 = System.currentTimeMillis();
        long time2 = System.currentTimeMillis();
        while (time2 - time1 < lastTime) {
            time2 = System.currentTimeMillis();
            heroAircraft.setShootNum(shootNum);
        }
        shootNum = 1;
        heroAircraft.setShootNum(shootNum);
        heroAircraft.setLimitBreaking(false);
    }
}
