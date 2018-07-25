package com.example.hyeon.part9_24;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> datas;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lab1_listview);

        datas = new ArrayList<>();  // aray adapter를 만들 준비.
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(adapter);

        //add~~~~~~~~~~~~~
        TelephonyManager telManager=(TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        telManager.listen(listener, PhoneStateListener.LISTEN_CALL_STATE|PhoneStateListener.LISTEN_SERVICE_STATE);

        datas.add("countryIos:"+telManager.getNetworkCountryIso()); // 국가정보
        datas.add("operatorName:"+telManager.getNetworkOperatorName()); // operator 이름
        if(telManager.getNetworkType()==TelephonyManager.NETWORK_TYPE_LTE){
            datas.add("networkType:LTE");           // lte인지 아닌지
        }else if(telManager.getNetworkType()==TelephonyManager.NETWORK_TYPE_HSDPA){
            datas.add("networkType:3G");
        }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)== PackageManager.PERMISSION_GRANTED){
            datas.add("PhoneNumber:"+telManager.getLine1Number());      //phone number 가져오고 --> READ_PHONE_STATE permission 필요하다.
        }else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 100);
        }

        checkNetwork();     // network가 wifi인지 mobile인지 판단

        checkWifi();    //wifi가 연결되었는지 wifi manager를 이용해서 판단.

        IntentFilter wifiFilter=new IntentFilter();
        wifiFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        wifiFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        wifiFilter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        registerReceiver(wifiReceiver, wifiFilter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(wifiReceiver);
    }

    PhoneStateListener listener = new PhoneStateListener() {    // 각각의service state에 따라서 array adapter에 data add
        @Override
        public void onServiceStateChanged(ServiceState serviceState) {
            //add~~~~~~~~~~~~~~~
            switch (serviceState.getState()){
                case ServiceState.STATE_EMERGENCY_ONLY:
                    datas.add("onServiceStateChanged STATE_EMERGENCY_ONLY");
                    break;
                case ServiceState.STATE_IN_SERVICE:
                    datas.add("onServiceStateChanged STATE_IN_SERVICE");
                    break;
                case ServiceState.STATE_OUT_OF_SERVICE:
                    datas.add("onServiceStateChanged STATE_OUT_OF_SERVICE");
                    break;
                case ServiceState.STATE_POWER_OFF:
                    datas.add("onServiceStateChanged STATE_POWER_OFF");
                    break;
                default:
                    datas.add("onServiceStateChanged Unknown");
                    break;
            }
            adapter.notifyDataSetChanged();         // dataset 이 변경되었다는걸 array adapter에게 알려줌.
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            //add~~~~~~~~~~~~~~
            switch (state){             //call state에 따라서 array adapter에 add해준다.
                case TelephonyManager.CALL_STATE_IDLE:
                    datas.add("onCallStateChanged : CALL_STATE_IDLE : "+incomingNumber);
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    datas.add("onCallStateChanged : CALL_STATE_RINGING : "+incomingNumber);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    datas.add("onCallStateChanged : CALL_STATE_OFFHOOK : "+incomingNumber);
                    break;
            }
            adapter.notifyDataSetChanged();
        }

    };

    private void checkNetwork() {
        //add~~~~~~~~~~~~~~~
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo != null){            //현재 wifi 에 연결된 건지 mobile에 연결된건지 알려주기.
            if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI){
                datas.add("Network Info : Online - "+networkInfo.getTypeName());
            }else if (networkInfo.getType()==ConnectivityManager.TYPE_MOBILE){
                datas.add("Network Info : Online - "+networkInfo.getTypeName());
            }
        }else {
            datas.add("Network Info : Offline");
        }
        adapter.notifyDataSetChanged();
    }

    private void checkWifi(){
        //add~~~~~~~~~~~~~~~~
        WifiManager wifiManager=(WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!wifiManager.isWifiEnabled()){       // wifi 가 disable한지 enable한지 WifiManager 이용하여 판단한다.
            datas.add("WifiManager : wifi disabled");
            if(wifiManager.getWifiState() != WifiManager.WIFI_STATE_ENABLED){
                wifiManager.setWifiEnabled(true);
            }
        }else {
            datas.add("WifiManager : wifi enabled");
        }
        adapter.notifyDataSetChanged();
    }

    BroadcastReceiver wifiReceiver=new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            //add~~~~~~~~~~~~~~~~~~~~
            if(intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)){
                int state=intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, -1);
                if(state==WifiManager.WIFI_STATE_ENABLED){
                    datas.add("WIFI_STATE_CHANGED_ACTION : enable");
                }else {
                    datas.add("WIFI_STATE_CHANGED_ACTION : disable");
                }
            }else if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)){
                NetworkInfo networkInfo=intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                WifiManager wifiManager=(WifiManager)context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo=wifiManager.getConnectionInfo();
                String ssid=wifiInfo.getSSID();
                if(networkInfo.getState()==NetworkInfo.State.CONNECTED){
                    datas.add("NETWORK_STATE_CHANGED_ACTION : connected..."+ssid);
                }else if(networkInfo.getState()==NetworkInfo.State.DISCONNECTED){
                    datas.add("NETWORK_STATE_CHANGED_ACTION : disconnected..."+ssid);
                }
            }else if(intent.getAction().equals(WifiManager.RSSI_CHANGED_ACTION)){
                datas.add("RSSI_CHANGED_ACTION");
            }
            adapter.notifyDataSetChanged();
        }
    };
}