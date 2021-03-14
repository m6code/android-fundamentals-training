package com.m6code.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {
    private static final String ACTION_CUSTOM_BROADCAST =
            "com.m6code.powerreceiver.ACTION_CUSTOM_BROADCAST";

    @Override
    public void onReceive(Context context, Intent intent) {
       String intentAction = intent.getAction();

       if(intentAction != null){
           String toastMessage = "unknown intent action";
           switch (intentAction){
               case Intent.ACTION_POWER_CONNECTED:
                   toastMessage = "Power connected!";
                   break;
               case Intent.ACTION_POWER_DISCONNECTED:
                   toastMessage = "Power disconnected!";
                   break;
               case ACTION_CUSTOM_BROADCAST:
                   toastMessage = "Custom Broadcast Received";
                   break;
               case Intent.ACTION_HEADSET_PLUG:
                   int state = intent.getIntExtra("state", 0);
                   if (state == 1){
                       toastMessage = "Headset Plugged In";
                   } else toastMessage = "Headset Unplugged";

                   break;
           }

           Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
       }
    }
}