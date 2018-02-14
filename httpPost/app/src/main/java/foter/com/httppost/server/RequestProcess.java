package foter.com.httppost.server;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import foter.com.httppost.DataCommunicator;


public class RequestProcess {

    public static Context context;
    public static DataCommunicator dc;

    public static void getData(RequestObject requestObject) throws Exception {
        if(context == null)
            throw new Exception("Context must be set!");
        valleyMethod(requestObject);

    }

    private static void processResult(String result) throws JSONException {
        List<ResponseObject> returnList = new ArrayList<>();

        Log.v("GRNC", "return json: " +result);

        JSONObject jsonObj = new JSONObject(result);
        JSONArray records = jsonObj.getJSONArray("records");

        for (int i = 0; i < records.length(); i++) {
            ResponseObject ro = new ResponseObject();

            JSONObject c = records.getJSONObject(i);
            JSONObject _id = c.getJSONObject("_id");
            ro.id = _id.getString("_id");
            ro.key = _id.getString("key");
            ro.value = _id.getString("value");
            ro.createdAt = _id.getString("createdAt");
            ro.totalCount = c.getInt("totalCount");

            returnList.add(ro);

        }
        dc.dataRetrieve(returnList);
    }

    private static void valleyMethod(final RequestObject ro){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://getir-bitaksi-hackathon.herokuapp.com/searchRecords";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        try {
                            processResult(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<>();
                params.put("startDate", ro.getStartDate()); //Add the data you'd like to send to the foter.com.httppost.server.
                params.put("endDate", ro.getEndDate()); //Add the data you'd like to send to the foter.com.httppost.server.
                params.put("minCount", ro.getMinCount() + ""); //Add the data you'd like to send to the foter.com.httppost.server.
                params.put("maxCount", ro.getMaxCount() + ""); //Add the data you'd like to send to the foter.com.httppost.server.

                return params;
            }
        };
        postRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }

        });
        queue.add(postRequest);
    }

}
