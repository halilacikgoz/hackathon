package foter.com.httppost;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import foter.com.httppost.server.RequestObject;
import foter.com.httppost.server.RequestProcess;
import foter.com.httppost.server.ResponseObject;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, DataCommunicator {

    String startDateString;
    String endDateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText minCount1 = (EditText) findViewById(R.id.editText);
        final EditText maxCount1 = (EditText) findViewById(R.id.editText2);
        Button button4= (Button) findViewById(R.id.button4);

        RequestProcess.dc = this;
        RequestProcess.context = getApplicationContext();

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String minCount2 = minCount1.getText().toString();
                String maxCount2 = maxCount1.getText().toString();


                if (minCount2.equals("") || maxCount2.equals("") ) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Değerleri girin !")
                            .setTitle("Eksik")
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                 int  minCount = Integer.parseInt(minCount2 + "");
                 int  maxCount = Integer.parseInt(maxCount2 + "");


//                    RequestObject requestObject = new RequestObject(startDateString, endDateString, minCount, maxCount);
//                    try {
//                        RequestProcess.context = getApplicationContext();
//                        List<ResponseObject> results = RequestProcess.getData(requestObject);
//                        Intent intent = new Intent(MainActivity.this, Lists.class);
//                        Lists.response = results;
//                        startActivity(intent);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }

                    try {
                        RequestProcess.getData(new RequestObject(startDateString, endDateString ,minCount, maxCount));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
            }
        });


        final DatePickerr datePickerr= new DatePickerr(this);
        Button startDate = (Button) findViewById(R.id.button);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerr.isStart=true;
                datePickerr.get("Start Date Picker");

            }
        });

        Button endDate= (Button) findViewById(R.id.button2);
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerr.isStart=false;
                datePickerr.get("End Date Picker");
            }
        });
    }

        @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH,month);
        calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        String startDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        if(DatePickerr.isStart) {
            TextView textView = (TextView) findViewById(R.id.textView);

            startDateString = year+"-"+month+"-"+dayOfMonth;
            textView.setText(startDateString);

        }
        else {
            TextView textView = (TextView) findViewById(R.id.textView1);

            endDateString = year+"-"+month+"-"+dayOfMonth;
            textView.setText(endDateString);
        }

    }

    @Override
    public void dataRetrieve(List<ResponseObject> ro) {
        Lists.response = ro;
        Log.v("AAA",ro.toString());
        Intent intent = new Intent(MainActivity.this, Lists.class);
        startActivity(intent);
    }
}
