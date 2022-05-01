package edu.hitsz.data;

import java.io.IOException;
import java.util.List;

public interface ScoreDao {
    public void keepScore(int score,String name) throws IOException;
    public void keepScore(List<Data> dataList) throws IOException;
    public List<Data> getRankingList();
}
