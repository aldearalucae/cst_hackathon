package com.davidrosu.ciucaslapet;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    static final byte NEW_LINE = 10;
    static final byte CR_NEW_LINE = 13;
    static BluetoothSocket BLUETOOTH_SOCKET = null;

    public void openRegisterActivity (View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void openLoginActivity (View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        System.out.println(bluetoothAdapter.getBondedDevices());

        BluetoothDevice bluetoothDevice = bluetoothAdapter.getRemoteDevice("98:D3:61:FD:5D:CE");

        do {
            try {
                BLUETOOTH_SOCKET = bluetoothDevice.createRfcommSocketToServiceRecord(mUUID);

                BLUETOOTH_SOCKET.connect();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (!BLUETOOTH_SOCKET.isConnected());


    }
}
