package edu.hitsz.application;

import java.io.IOException;

public class Game_Normal extends GameTemplate{
    public Game_Normal(boolean musicActive) throws IOException {
        super(musicActive);
    }

    @Override
    protected void setDifficulty() {
        enemyshootInterval = 40;
        enemyshootDuration = 1500;
        cycleDuration = 400;
        mobRate = 13;
        mobHp = 20;
        mobSpeedy = 4;
        eliteHp = 60;
        eliteSpeedy = 1.5;
        bossHp = 800;
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
