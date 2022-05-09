package edu.hitsz.application.gui;

import edu.hitsz.data.ScoreDaolmpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class RecordWindow {
    private JFrame frame = new JFrame();
    private JButton OkButton;
    private JButton CanselButton;
    private JTextField textField1;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    ScoreDaolmpl scoreDaolmpl = new ScoreDaolmpl(Main.difficulty);
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public RecordWindow(int score) throws IOException {

        OkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    scoreDaolmpl.keepScore(score,textField1.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                frame.dispose();
                Main.diposeFrame();
                try {
                    ScoreTable.setScoreTable();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        CanselButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                Main.diposeFrame();
            }
        });

    }

    public void setRecordWindow() throws IOException {
        frame.setSize(300,220);
        frame.setContentPane(panel1);
        frame.dispose();
        frame.setLocation(((int)screenSize.getWidth()-300)/2,((int)screenSize.getHeight()-220)/2);
        frame.setVisible(true);
    }


}
