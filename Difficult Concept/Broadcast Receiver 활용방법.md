***Broadcast Receiver***
���ε�ĳ��Ʈ ���ù��� intent�� ����ȴ�.

��Ƽ��Ƽ : ȭ���� ��µǴ� ������Ʈ�̹Ƿ� intent�� �����ϸ� �ϳ��� ����Ǵ� ���� �翬�ϴ�.
Broadcast Receiver : intent�� ���� ����Ǿ�� �� Broadcast Receiver�� ���ٰ� �ϴ��� ������ �߻����� �ʴ´�. 
		intent�߻����� ������ broadcast receiver�� ��������� ��� �����Ѵ�. 
	--> ������ ����, ������ �� ����!

* �ۼ���� *
public class MyReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast toast = Toast.makeText(context, "I am BroadcastReceiver", Toast.Length_Short).show();
	}
}

--> BroadcastReceiver�� ��� manifest�� ����� ���־���Ѵ�.
--> �±׸� ���־��ٸ� activity�� ���������� �Ͻ��� ����Ʈ�� ���� ����Ǿ���Ѵٸ� <intent-filter> �� ��ϵ� ���� �ִ�.
 �̷��� �Ͻ�������Ʈ�� ������� ��ε�ĳ��Ʈ���ù��� ��� sendBroadcast()�Լ��� ����Ʈ�� �߽̽��� �����Ѵ�.
Intent intent = new Intent(this, MyReceiver.class);
sendBroadcast(intent);


2. System Broadcast intent
--> Broadcast Receiver�� ��� �۳��ο����� ���� �������� ���� �ý��� ��Ȳ�� �����Ҷ��� ���� ���δ�.
1) ���ÿϷ�
<receiver android:name = ".MyReceiver">
	<intent-filter>
		<action android:name = "android.intent.action.BOOT_COMPLETED" />
	</intent-filter>
</receiver>
--> ���ÿϷ������ Broadcast Receiver�� �����ϰ� �Ϸ��� BOOT_COMPLETED �۹̼��� ��ϵǾ��־���Ѵ�.

2) ȭ�� on/off
--> Manifest�� <receiver> �±׸� ����ϸ� �ȵȴ�. �������� class���� ����ؾ߽���ȴ�.
BroadcastReceiver brOn = new BroadcastReceiver() {
	@Override
	public void onReceive(Context context, Intent intent) {
		//
	}
};
registerReceiver(brOn, new IntentFilter(Intent.ACTION_SCREEN_ON); --> registerReceiver����Ͽ� �ý��ۿ� ����Ѵ�.

3) ��ȭ���� �߽�
<uses-permission --> PROCESS_OUTGOING_CALLS / READ_PHONE_STATE  �ΰ��� �۹̼��� ������־���Ѵ�.
<receiver �±׷� ����� ���� �Ŀ�
if(action.equals("android.intent.action.NEW_OUTGOING_CALL") 
	String phoneNum = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
else if(action.equals("android.intent.action.PHONE_STATE") {
	Bundle bundle = intent.getExtras();
	String state = bundle.getString(TelephonyManager.EXTRA_STATE);
}

4) ���͸�
registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_POWER_CONNECTED / ACTION_POWER_DISCONNECTED)
-->
BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
	String action = intent.getAction();
	if(action.equals(Intent.ACTION_POWER_CONNECTED)...

//���͸��� ��Ȳ �ľ��ϱ�
IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
Intent batteryStatus = registerReceiver(null, ifilter);

int status = batteryStatus.getIntExtra(BatteryManaer.EXTRA_STATUS, -1);
boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;

int chargetPlug = batteryStatus.getIntExtra(BetteryManager.EXTRA_PLUGGED, -1);

float batteryPct = (level/(float)scale) * 100;
	