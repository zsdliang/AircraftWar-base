package edu.hitsz.application;

import javax.sound.sampled.*;
import java.io.*;

public class MusicThread extends Thread {


    //音频文件名
    private String filename;
    private AudioFormat audioFormat;
    private byte[] samples;
    private Object lock = new Object();
    private volatile boolean isStop = false;
    private boolean singlePlay = true;
    private boolean musicActive;
    private boolean isRunning = true;

    public MusicThread(String filename,boolean musicActive) {
        //初始化filename
        this.filename = filename;
        this.musicActive = musicActive;
        reverseMusic();
    }

    public void reverseMusic() {
        try {
            //定义一个AudioInputStream用于接收输入的音频数据，使用AudioSystem来获取音频的音频输入流
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File(filename));
            //用AudioFormat来获取AudioInputStream的格式
            audioFormat = stream.getFormat();
            samples = getSamples(stream);
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public byte[] getSamples(AudioInputStream stream) {
        int size = (int) (stream.getFrameLength() * audioFormat.getFrameSize());
        byte[] samples = new byte[size];
        DataInputStream dataInputStream = new DataInputStream(stream);
        try {
            dataInputStream.readFully(samples);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return samples;
    }

    public void play(InputStream source) {
        int size = 15000;
//                (int) (audioFormat.getFrameSize() * audioFormat.getSampleRate());

        byte[] buffer = new byte[size];
        //源数据行SoureDataLine是可以写入数据的数据行
        SourceDataLine dataLine = null;
        //获取受数据行支持的音频格式DataLine.info
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        try {
            dataLine = (SourceDataLine) AudioSystem.getLine(info);
            dataLine.open(audioFormat, size);
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        dataLine.start();
        try {
            int numBytesRead = 0;
            while (numBytesRead != -1 ) {
				//从音频流读取指定的最大数量的数据字节，并将其放入缓冲区中
                numBytesRead =
                        source.read(buffer, 0, buffer.length);
				//通过此源数据行将数据写入混频器
                if (numBytesRead != -1) {
                    dataLine.write(buffer, 0, numBytesRead);
                }
                if(isStop) {
                    break;
                }
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        dataLine.drain();
        dataLine.close();

    }

    @Override
    public void run() {

        while(musicActive) {
            reverseMusic();
            InputStream stream = new ByteArrayInputStream(samples) ;
            if(!isStop) {
                play(stream);
            }
            if(singlePlay) {
                break;
            }
        }
    }

    public void stopPlay() {
        this.isStop = true;
    }
    public void reStart() {
        this.isStop = false;
    }
    public void killThread() {
        this.isStop = true;
        this.isRunning = false;
        this.musicActive = false;
    }

    public void setSinglePlay(boolean singlePlay) {
        this.singlePlay = singlePlay;
    }

}


