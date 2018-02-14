package foter.com.httppost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import foter.com.httppost.server.ResponseObject;

public class Lists extends AppCompatActivity {


    public static List<ResponseObject> response = null;
   public static  List<String> stringList= new ArrayList<>();

    public static int ilk, son, page;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lists);
        page =1;

        final TextView pageText = (TextView) findViewById(R.id.textView2);
        pageText.setText(page+"");
        ilk = 0;
        son = response.size() > 10 ? 10 : response.size() -1;


        for(ResponseObject ro: response){
            stringList.add("ID: "+ro.id+"\n\n"+"KEY: "+ro.key+"\n\n"+"VALUE: "+ro.value+"\n\n"+"CREATED AT: "+ro.createdAt);

        }

        ListView listView = (ListView) findViewById(R.id.ListView);

        final ArrayAdapter<String> veriAdaptoru=new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, android.R.id.text1, stringList.subList(ilk,
                        son));
        listView.setAdapter(veriAdaptoru);

        Button next = (Button) findViewById(R.id.button5);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;
                pageText.setText(page+"");
                veriAdaptoru.clear();
                ilk += 10;
                son += 10;
                son = (son > response.size()-1 ? response.size() -1: son);
                ilk = (ilk > response.size()-1 ? response.size() -1: ilk);

                for(int i = 0; i < (son - ilk > 10 ? 10 : son - ilk); i++){
                    ResponseObject ro = response.get(ilk + i);
                    veriAdaptoru.add("ID: "+ro.id+"\n\n"+"KEY: "+ro.key+"\n\n"+"VALUE: "+ro.value+"\n\n"+"CREATED AT: "+ro.createdAt);
                }
            }
        });

        Button prev = (Button) findViewById(R.id.button3);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page--;
                pageText.setText(page+"");
                veriAdaptoru.clear();
                ilk -= 10;
                son -= 10;
                ilk = ilk < 0 ? 0 : ilk;
                son = son < 0 ? 0 : son;

                    for (int i = 0; i < (son - ilk > 10 ? 10 : son - ilk); i++) {
                        ResponseObject ro = response.get(ilk + i);
                        veriAdaptoru.add("ID: " + ro.id + "\n\n" + "KEY: " + ro.key + "\n\n" + "VALUE: " + ro.value + "\n\n" + "CREATED AT: " + ro.createdAt);
                    }

            }
        });




    }
}
