package com.davidrosu.ciucaslapet;

import android.bluetooth.BluetoothSocket;
import android.media.AudioManager;
import android.media.ToneGenerator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataMiner extends Thread {
    public static boolean isAlive = true;
    private BluetoothSocket bluetoothSocket;

    public DataMiner(BluetoothSocket bluetoothSocket) {
        super();
        this.bluetoothSocket = bluetoothSocket;
    }

    public static int UP = 1;
    public static int DOWN = 0;
    public static int STATE = UP;
    public static int COUNTER = 0;

    @Override
    public void run() {
        try {
            InputStream inputStream = bluetoothSocket.getInputStream();
            inputStream.skip(inputStream.available());

            DataMiner.COUNTER = 0;
            DataMiner.STATE = UP;

            while (isAlive) {
                List<Byte> list = new ArrayList<>();
                byte b = (byte) inputStream.read();

                while (b != 13) {
                    list.add(b);
                    b = (byte) inputStream.read();
                }

                inputStream.read();

                Data data = new Data(list);
//                System.out.println(data);
                System.out.println(COUNTER);
                if (data.isDOWN() && STATE == UP) { HomeActivity.counterView.post(new Runnable() {
                    public void run() {
                        HomeActivity.counterView.setText(String.valueOf(COUNTER));
                    }
                });

//                    COUNTER++;
                    STATE = DOWN;
                } else if (data.isUP() && STATE == DOWN) {

                    HomeActivity.counterView.post(new Runnable() {
                        public void run() {
                            HomeActivity.counterView.setText(String.valueOf(COUNTER));
                        }
                    });


                    COUNTER++;
//                    ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
//                    toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
                    STATE = UP;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
