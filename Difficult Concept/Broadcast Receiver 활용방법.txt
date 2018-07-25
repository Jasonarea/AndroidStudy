***Broadcast Receiver***
브브로드캐스트 리시버는 intent에 수행된다.

액티비티 : 화면이 출력되는 컴포넌트이므로 intent를 실행하면 하나만 실행되는 것이 당연하다.
Broadcast Receiver : intent에 의해 실행되어야 할 Broadcast Receiver가 없다고 하더라도 에러가 발생하지 않는다. 
		intent발생으로 실행할 broadcast receiver가 여러개라면 모두 실행한다. 
	--> 없으면 말구, 있으면 다 실행!

* 작성방법 *
public class MyReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast toast = Toast.makeText(context, "I am BroadcastReceiver", Toast.Length_Short).show();
	}
}

--> BroadcastReceiver의 경우 manifest에 등록을 해주어야한다.
--> 태그를 해주었다면 activity와 마찬가지로 암시적 인텐트에 의해 실행되어야한다면 <intent-filter> 가 등록될 수도 있다.
 이렇게 암시적인텐트로 만들어진 브로드캐스트리시버의 경우 sendBroadcast()함수로 인텐트를 발싱시켜 실행한다.
Intent intent = new Intent(this, MyReceiver.class);
sendBroadcast(intent);


2. System Broadcast intent
--> Broadcast Receiver의 경우 앱내부에서도 많이 사용되지만 각종 시스템 상황을 감지할때도 많이 쓰인다.
1) 부팅완료
<receiver android:name = ".MyReceiver">
	<intent-filter>
		<action android:name = "android.intent.action.BOOT_COMPLETED" />
	</intent-filter>
</receiver>
--> 부팅완료시점에 Broadcast Receiver가 동작하게 하려면 BOOT_COMPLETED 퍼미션이 등록되어있어야한다.

2) 화면 on/off
--> Manifest에 <receiver> 태그를 등록하면 안된다. 동적으로 class에서 등록해야실행된다.
BroadcastReceiver brOn = new BroadcastReceiver() {
	@Override
	public void onReceive(Context context, Intent intent) {
		//
	}
};
registerReceiver(brOn, new IntentFilter(Intent.ACTION_SCREEN_ON); --> registerReceiver사용하여 시스템에 등록한다.

3) 전화수신 발신
<uses-permission --> PROCESS_OUTGOING_CALLS / READ_PHONE_STATE  두개의 퍼미션을 등록해주어야한다.
<receiver 태그로 등록을 해준 후에
if(action.equals("android.intent.action.NEW_OUTGOING_CALL") 
	String phoneNum = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
else if(action.equals("android.intent.action.PHONE_STATE") {
	Bundle bundle = intent.getExtras();
	String state = bundle.getString(TelephonyManager.EXTRA_STATE);
}

4) 배터리
registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_POWER_CONNECTED / ACTION_POWER_DISCONNECTED)
-->
BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
	String action = intent.getAction();
	if(action.equals(Intent.ACTION_POWER_CONNECTED)...

//배터리의 상황 파악하기
IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
Intent batteryStatus = registerReceiver(null, ifilter);

int status = batteryStatus.getIntExtra(BatteryManaer.EXTRA_STATUS, -1);
boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;

int chargetPlug = batteryStatus.getIntExtra(BetteryManager.EXTRA_PLUGGED, -1);

float batteryPct = (level/(float)scale) * 100;
	