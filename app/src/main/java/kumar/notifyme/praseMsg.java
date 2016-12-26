package kumar.notifyme;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AJ047884 on 12/8/2016.
 */

public class praseMsg {


    static  String msg = "" ;
    static  String otp ="";
    static  final String tag ="notifyme.praseMsg";

    StringBuilder numbers = new StringBuilder("");

    public praseMsg(String msg)
    {
        this.msg= new String(msg);
    }


    public boolean lookForNumber()
    {
        String regex ="\\d{10}";
        Pattern pattren = Pattern.compile(regex);
        Matcher matcher = pattren.matcher(this.msg);   // get a matcher object

        try{
            while(matcher.find())
            {
                Log.d(tag," number find(): " + matcher.find());
                Log.d(tag , "number start(): "+ matcher.start());
                Log.d(tag , "number end(): "+ matcher.end());
                Log.d(tag,"number match: " + matcher.group());
                numbers.append(matcher.group()+" ");
            }

        }catch(RuntimeException e)
        {
            Log.d(tag,"runtime exception in paresmsg.java : since no number found");
        }
        catch(Exception e)
        {
            Log.d(tag,"other exception : since no number  found. ");
        }
//        irrespect of number found or not , this  information must be passed
        finally {
            if(numbers.length()==0)
            {
                return false;
            }

            return true;

        }

    }




}
