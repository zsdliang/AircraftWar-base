package edu.hitsz.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreDaolmpl implements ScoreDao {
    private List<Data> dataList = null;
    private File file = new File("./record.ser");

    public ScoreDaolmpl() throws IOException {

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
    public void keepScore(int score) throws IOException {
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();
        s.close();
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

    ;

    @Override
    public void getRankingList() {
        dataList.sort((o1, o2) -> {
            if (o1.getScore() >= o2.getScore()) {
                return -1;
            } else {
                return 1;
            }
        });
        for (Data i : dataList) {
            System.out.println("score:" + i.getScore() + "name:" + i.getName());
        }
    }
}
