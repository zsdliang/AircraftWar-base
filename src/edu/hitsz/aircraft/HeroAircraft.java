package edu.hitsz.aircraft;

import edu.hitsz.application.ImageManager;
import edu.hitsz.application.gui.Main;

/**
 * 英雄飞机，游戏玩家操控
 * @author hitsz
 */
public class HeroAircraft extends AbstractAircraft {
    private boolean isLimitBreaking = false;
    private HeroAircraft(double locationX, double locationY, double speedX, double speedY, int hp) {
        super(locationX, locationY, speedX, speedY, hp);
    }

    @Override
    public void forward() {
        // 英雄机由鼠标控制，不通过forward函数移动
    }


    private static HeroAircraft instance = null;
    public static synchronized HeroAircraft getInstance() {
        if(instance == null) {
            instance = new HeroAircraft(Main.WINDOW_WIDTH / 2,
                    Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight() ,
                    0, 0, 100);
        }
        return instance;
    }

    public void setInitLocation(){
        this.locationX = Main.WINDOW_WIDTH / 2;
        this.locationY = Main.WINDOW_HEIGHT - ImageManager.HERO_IMAGE.getHeight();
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
        this.isValid = true;
    }

    public boolean isLimitBreaking() {
        return isLimitBreaking;
    }
    public void setLimitBreaking(boolean isLimitBreaking) {
        this.isLimitBreaking = isLimitBreaking;
    }
}
