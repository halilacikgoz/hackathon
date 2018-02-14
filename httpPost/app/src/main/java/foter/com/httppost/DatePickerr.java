package foter.com.httppost;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

/**
 * Created by pc on 13.02.2018.
 */

public class DatePickerr {
    public static boolean isStart;
    private FragmentActivity factivity;
    public DatePickerr(FragmentActivity factivity){
        this.factivity=factivity;
    }
    public void get(String text ){
        android.support.v4.app.DialogFragment endDatePicker= new DatePickerFragment();
        endDatePicker.show(factivity.getSupportFragmentManager(),text);
    }
}
