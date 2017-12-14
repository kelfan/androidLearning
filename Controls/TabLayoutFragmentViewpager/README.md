# Android Broadcast Intent and Broadcast Receiver
https://www.youtube.com/watch?v=lGwDARPSCkE

# Broadcast Receiver 
1. new -> other -> Broadcast Receiver 
1. Send 
```java 
    public void onBtnSendBroadcastClick(View view) {
        Intent intent = new Intent("vn.vtgames.broadcastsender");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        this.sendBroadcast(intent);
    }
```
1. Receive 
```java 
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");
        String action = intent.getAction();
        Toast.makeText(context, "detected: "+action, Toast.LENGTH_SHORT).show();

    }
}
```
1. debug 
    1. launch Activity = nothing or do not launch Activity 
    1. 