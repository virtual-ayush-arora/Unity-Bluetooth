package com.ayush.unity;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.InputStream;

class Reading implements Runnable {

    private Thread t;
    private BluetoothSocket bluetoothSocket;

    Reading(BluetoothSocket bluetoothSocket) throws Exception {
        this.bluetoothSocket = bluetoothSocket;
        t = new Thread(this, "Reading");
        t.start();
    }

    @Override
    public void run() {
        InputStream inputStream = null;
        try {
            inputStream = bluetoothSocket.getInputStream();
            byte[] buffer;
            int bytes;
            bytes = inputStream.available();
            if (bytes > 0) {
                buffer = new byte[bytes];
                inputStream.read(buffer);
                final String string = new String(buffer);
                Log.d("zxcvbnm",string);
                Main.Message = string;
                Main.IsDataAvailable = true;
            }
        } catch (Exception e) {
            //ERROR
        }
    }
}