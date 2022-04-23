package edu.hitsz.data;

import java.io.IOException;

public interface ScoreDao {
    public void keepScore(int score) throws IOException;
    public void getRankingList();
}
