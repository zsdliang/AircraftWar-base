package edu.hitsz.application.gui;

import edu.hitsz.data.Data;
import edu.hitsz.data.ScoreDaolmpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class ScoreTable {
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 500;
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private JPanel panel1;
    private JTable table1;
    private JButton deleteButton;
    private JScrollPane scrollpane1;

    public ScoreTable(int difficulty) throws IOException {
        String[] columnName = {"排名","分数","姓名"};

        ScoreDaolmpl scoreDaolmpl = new ScoreDaolmpl(difficulty);
        List<Data> datalist = scoreDaolmpl.getRankingList();
        String[][]dataTable = new String[datalist.size()][3];
        for(int i =0;i< datalist.size();i++) {
            dataTable[i][0] = Integer.toString(i+1);
            dataTable[i][1] = Integer.toString(datalist.get(i).getScore());
            dataTable[i][2] = datalist.get(i).getName();
        }
        //表格模型
        DefaultTableModel model = new DefaultTableModel(dataTable, columnName){
            @Override
            public boolean isCellEditable(int row, int col){
                return false;
            }
        };
        //JTable 并不存储自己的数据，而是从表格模型那里获取它的数据
        this.table1.setModel(model);
        scrollpane1.setViewportView(this.table1);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                System.out.println(row);
                if(row != -1){
                    model.removeRow(row);
                    datalist.remove(row);
                    try {
                        scoreDaolmpl.keepScore(datalist);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

    }

    public static void setScoreTable() throws IOException {
        JFrame frame = new JFrame("ScoreTable");
        frame.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        frame.setLocation(((int)screenSize.getWidth()-WINDOW_WIDTH)/2,((int)screenSize.getHeight()-WINDOW_HEIGHT)/2);
        frame.setContentPane(new ScoreTable(Main.difficulty).panel1);
        frame.setVisible(true);
    }
}
