package edu.hitsz.application;

import java.io.IOException;

public class Game_Simple extends GameTemplate{
    public Game_Simple(boolean musicActive) throws IOException {
        super(musicActive);
    }

    @Override
    protected void setDifficulty() {
        enemyshootInterval =40;
        enemyshootDuration = 2000;
        mobRate = 17;
        mobHp = 20;
        mobSpeedy =  3;
        eliteHp = 40;
        eliteSpeedy = 1;
        bossHp = 400;
    }

    @Override
    protected void increaseDifficulty() {
    }
}
