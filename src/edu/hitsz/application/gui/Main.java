package edu.hitsz.application.gui;

import edu.hitsz.application.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class Main {
    public static final int WINDOW_WIDTH = 512;
    public static final int WINDOW_HEIGHT = 768;
    public static String map;
    public static int difficulty;
    public final int SIMPLE = 1;
    public final int NORMAL = 2;
    public final int DIFFICULT = 3;

    private static JFrame frame = new JFrame();
    private JPanel panel1;
    private JButton simpleButton;
    private JButton normalButton;
    private JButton difficultButton;
    private JRadioButton radioButton1;

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private boolean musicActive = true;
    private static boolean isGameRuning = false;
    private boolean isRadioButtonClicked = true;



    public Main() throws IOException {
        System.out.println(screenSize.getWidth());
        System.out.println(screenSize.getHeight());
        simpleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if(!isGameRuning) {
                        difficulty = SIMPLE;
                        map = "src/images/bg.jpg";
                        ImageManager.setMap();
                        gameFrame(frame,SIMPLE);
                        isGameRuning = true;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        normalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if(!isGameRuning) {
                        difficulty = NORMAL;
                        map = "src/images/bg4.jpg";
                        ImageManager.setMap();
                        gameFrame(frame,NORMAL);
                        isGameRuning = true;

                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        difficultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    if(!isGameRuning) {
                        difficulty = DIFFICULT;
                        map = "src/images/bg5.jpg";
                        ImageManager.setMap();
                        gameFrame(frame,DIFFICULT);
                        isGameRuning = true;
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        radioButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!isRadioButtonClicked) {
                    isRadioButtonClicked = true;
                    musicActive = true;
                }
                else {
                    isRadioButtonClicked = false;
                    musicActive = false;
                }
            }
        });
    }

    public void gameFrame(JFrame frame,int difficulty) throws IOException {
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(true);
        //设置窗口的大小和位置,居中放置
        frame.setBounds(((int) screenSize.getWidth() - WINDOW_WIDTH) / 2, 0,
                WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GameTemplate game = difficulty==SIMPLE?(new Game_Simple(musicActive)):(difficulty==NORMAL?(new Game_Normal(musicActive)):(new Game_Difficult(musicActive)));
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    game.setGameOver();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    game.setRecordWindow();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                isGameRuning = false;

            }
        });
        frame.setContentPane(game);
        frame.setVisible(true);
        game.action();
    }

    public static void diposeFrame() {
        frame.dispose();
        isGameRuning = false;
    }
    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame();
        frame.setSize(400,500);
        frame.setContentPane(new Main().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(((int)screenSize.getWidth()-400)/2,((int)screenSize.getHeight()-500)/2);

//        frame.pack();
        frame.setVisible(true);
    }
}