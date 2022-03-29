package edu.hitsz.EnemyFactory;

import edu.hitsz.aircraft.AbstractAircraft;
import edu.hitsz.aircraft.ElitEnemy;
import edu.hitsz.application.ImageManager;
import edu.hitsz.application.Main;

public class ElitFactory implements EnemyFactory{
    @Override
    public AbstractAircraft creatEnemy() {
        return new ElitEnemy((int) (Math.random() * (Main.WINDOW_WIDTH - ImageManager.ELIT_ENEMY_IMAGE.getWidth())) * 1,
                (int) (Math.random() * Main.WINDOW_HEIGHT * 0.2) * 1,
                3,
                1,
                50);
    }
}
