package com.ayush.unity;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.RequiresApi;

import java.util.Set;
import java.util.UUID;

public class Bluetooth{

    private BluetoothAdapter myBluetooth = null;
    private BluetoothSocket bluetoothSocket = null;
    private BluetoothServerSocket btSocket = null;
    private boolean isBtConnected = false;
    private static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private String Address;

    //NEED TO GIVE DEVICE ADDRESS WHILE CREATING OBJECT
    Bluetooth(String address){
        this.Address = address;
        new ConnectBT().execute();
    }

    class ConnectBT extends AsyncTask<Void, Void, Void> {

        private boolean mConnectSuccessful = true;

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Void doInBackground(Void... devices) {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    Set<BluetoothDevice> pairedDevices = myBluetooth.getBondedDevices();
                    BluetoothDevice bluetoothDevice = null;
                    for (BluetoothDevice Device : pairedDevices) {
                        if (Device.getAddress().equals(Address)) {
                            bluetoothDevice = Device;
                            break;
                        }
                    }
                    BluetoothSocket bs = bluetoothDevice.createRfcommSocketToServiceRecord(myUUID);
                    bs.connect();
                }
            } catch (Exception e) {
                //ERROR
                mConnectSuccessful = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(mConnectSuccessful) {
                isBtConnected = true;
                try {
                    btSocket = myBluetooth.listenUsingRfcommWithServiceRecord("myservice", myUUID);
                    bluetoothSocket = btSocket.accept();
                    Reading reading = new Reading(bluetoothSocket);
                    Main.IsConnectedNow = true;
                } catch (Exception e) {
                    //ERROR
                }
            }
            else {
                isBtConnected = false;
            }
        }
    }
}