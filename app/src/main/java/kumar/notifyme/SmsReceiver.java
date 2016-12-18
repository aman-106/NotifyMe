package kumar.notifyme;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    public static  final String tag = "notifyme.SmsReceiver";
    public static  final String SmsAction = "android.provider.Telephony.SMS_RECEIVED";
    public SmsMessage currentMsg ;
    StringBuilder msgString = new StringBuilder("")  ;
    String msgContent = new String("");

    public SmsReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        // an Intent broadcast.
        String action = intent.getAction();
        Log.d(tag,"actoin must be sms  telephony:"+ action);
        if(action.equals(SmsAction))
        {
            Bundle bundle = intent.getExtras();
            if(bundle!=null)
            {
                Object[] pduObj = (Object[]) bundle.get("pdus");
                Log.i(tag,"message length:"+pduObj.length );

                final String format = bundle.getString("format");

                Log.d(tag,"sms format:"+format);
                Log.d(tag,"build version_sdk: "+Build.VERSION.SDK_INT);


                int iter=0;
                for(;iter<pduObj.length; iter++)
                {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                       currentMsg = SmsMessage.createFromPdu((byte[]) pduObj[iter], format);
                    }
                    else
                    {
                        currentMsg = SmsMessage.createFromPdu((byte[])pduObj[iter]);
                    }

//                    msgString.append("from: "+ currentMsg.getDisplayOriginatingAddress());
                    msgString.append(currentMsg.getDisplayMessageBody());

                }

                Log.d(tag,"message recorded: " + msgString);
                this.forwardMsgToProcess(msgString.toString());
                if(!msgContent.isEmpty())
                {
                    Log.d(tag,"start service");
                    this.passInfoToService(context);
                }
            }
            else{
                Log.d(tag, "bundle is null");
            }
        }
        else{
            Log.d(tag, "action intented not same.");
        }
    }

    public void forwardMsgToProcess(String msgString) {
        Log.d(tag,"process the msg to look for numbers");
        praseMsg parsemsg = new praseMsg(msgString);
        if(parsemsg.lookForNumber());
        {
            this.msgContent = parsemsg.numbers.toString();
        }
        Log.d(tag,"numbers: " + parsemsg.numbers.toString());


    }

    private void passInfoToService(Context context) {
        Intent intent = new Intent(context ,smsReceiverService.class );
        intent.putExtra("msgcontent", msgContent);
        context.startService(intent);

    }
}
