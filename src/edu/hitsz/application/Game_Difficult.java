package edu.hitsz.application;

import java.io.IOException;

public class Game_Difficult extends GameTemplate{
    public Game_Difficult(boolean musicActive) throws IOException {
        super(musicActive);
    }

    @Override
    protected void setDifficulty() {
        enemyshootInterval = 40;
        enemyshootDuration = 1200;
        cycleDuration = 200;
        mobRate = 5;
        mobHp = 40;
        mobSpeedy =5;
        eliteHp =80;
        eliteSpeedy =2;
        bossHp =1200;
    }

    @Override
    protected void increaseDifficulty() {
        if(time%20000 == 0) {
            if(mobRate>0) {
                mobRate--;
            }
            if(enemyshootDuration > 0) {
                enemyshootDuration -= 50;
            }
            eliteHp += 10;
            mobHp += 10;
            bossHp += 200;
            mobSpeedy += 0.5;
            eliteSpeedy += 0.5;
        }
    }
}
