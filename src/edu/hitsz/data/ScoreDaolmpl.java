package edu.hitsz.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreDaolmpl implements ScoreDao {
    private String SIMPLE_FILE = "./record_simple.ser";
    private String NORMAL_FILE = "./record_normal.ser";
    private String DIFFICULT_FILE = "./record_difficult.ser";
    private List<Data> dataList = null;
    private File file;

    public ScoreDaolmpl(int difficulty) throws IOException {
        if(difficulty == 1) {
            file = new File(SIMPLE_FILE);
        }
        else if(difficulty == 2) {
            file = new File(NORMAL_FILE);
        }
        else {
            file = new File(DIFFICULT_FILE);
        }
        FileInputStream filein = null;
        try {
            if (file.exists()) {
                filein = new FileInputStream(file);
                ObjectInputStream in = new ObjectInputStream(filein);
                dataList = (List<Data>) in.readObject();
            }
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }
        if (dataList == null) {
            dataList = new ArrayList<Data>();
        }

        if (filein != null) {
            filein.close();
        }


    }


    @Override
    public void keepScore(int score,String name) throws IOException {
        Data data = new Data(score, name);
        dataList.add(data);

        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileout = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileout);
        out.writeObject(dataList);
        fileout.close();
    }

    @Override
    public void keepScore(List<Data> dataList) throws IOException{
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileout = new FileOutputStream(file);
        ObjectOutputStream out = new ObjectOutputStream(fileout);
        out.writeObject(dataList);
        fileout.close();
    }

    ;

    @Override
    public List<Data> getRankingList() {

        dataList.sort((o1, o2) -> {
            if (o1.getScore() >= o2.getScore()) {
                return -1;
            } else {
                return 1;
            }
        });
        return dataList;
    }

}
