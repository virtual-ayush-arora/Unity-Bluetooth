package com.ayush.unity;

public  class Main  {

    public volatile static String Message = "";
    public volatile static boolean IsConnectedNow = false;
    public volatile static boolean IsDataAvailable = false;
    public static final String Address ="FC:A8:9A:00:03:15";
    //CONNECTS BLUETOOTH DEVICE WITH PROVIDED ADDRESS
    public int Connect(){
        
        Bluetooth bluetooth = new Bluetooth(Address);
        return 1;
    }

    //WHEN CALLED SHOW STATUS OF CONNECTION
    public boolean IsNowConnected(){
        return IsConnectedNow;
    }

    //CALL THIS METHOD AFTER CONNECTION IS SUCCESSFUL
    public String Listen(){
        return Message;
    }

    //WHEN CALLED SHOW STATUS OF DATA
    public boolean IsNowDataAvailable(){
        return IsDataAvailable;
    }
}
