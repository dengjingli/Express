package com.swufeedu.express;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.swufeedu.express.Util.KdApiSearchDemo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private String TAG = "ListActivity";
    private String LogisticCode;
    private String ShipperCode;
    private String State;
    private String Reason = null;//判断符
    private int StateToInt;
    private Boolean Success;
    private List<String> stations;
    private List<String>times;

    private List<Item> items =new ArrayList<>();

    private RecyclerView recyclerView;

    private ImageView companyImg;
    private TextView stateText;
    private TextView search_Num;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        companyImg = (ImageView)findViewById(R.id.company_img);
        stateText = (TextView)findViewById(R.id.state_text);
        search_Num = (TextView)findViewById(R.id.search_num);
        stations = new ArrayList<>();
        times = new ArrayList<>();

        recyclerView  =(RecyclerView)findViewById(R.id.recycle_view_trace);

        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        final String code = preferences.getString("code","NUll");
        final String num = preferences.getString("number","Null");
        Log.i(TAG, "code  "+ code);
        Log.i(TAG, "number  " + num);

        new Thread(new Runnable() {


            @Override
            public void run() {
                try {
                    Log.i(TAG, "run: run()......");
                    KdApiSearchDemo api = new KdApiSearchDemo();
                    String respond = api.orderOnlineByJson(code, num);
                    Log.i(TAG, "respond:  " + respond);
                    JSONObject jsonObject = new JSONObject(respond);
                    JSONArray array = jsonObject.getJSONArray("Traces");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object = array.getJSONObject(i);
                        String AcceptStation = object.getString("AcceptStation");
                        stations.add(AcceptStation);
                        String AcceptTime = object.getString("AcceptTime");
                        times.add(AcceptTime);
                        Log.i(TAG, "AcceptStation:" + stations.get(i));
                        Log.i(TAG, "AcceptTime : " + times.get(i));
                        Log.i(TAG, "AcceptTime size: " + times.size());
                    }

                    LogisticCode = jsonObject.getString("LogisticCode");
                    Log.d(TAG, "LogisticCode: " + LogisticCode);

                    ShipperCode = jsonObject.getString("ShipperCode");
                    Log.d(TAG, "ShipperCode: " + ShipperCode);

                    State = jsonObject.getString("State");
                    Log.d(TAG, "State: " + State);
                    try {
                        StateToInt = Integer.parseInt(State);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Reason = jsonObject.getString("Reason");
                        Log.i(TAG, "Reason: " + Reason);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.i(TAG, "Reason: 正常查询无Reason 现在Reason：" + Reason);

                    }

                    Success = jsonObject.getBoolean("Success");
                    Log.i(TAG, "Success : " + Success);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(   Reason==null  ){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            showInfo();
                        }
                    });

                }//is
                else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showDefaultInfo();
                        }
                    });


                }

            }
        }).start();//runable
    }

    public void initItem(){

        for(int i =0;i < times.size();i++){

            Item item = new Item(stations.get(i),times.get(i));
            items.add(item);

        }

    }

    public void showTraceInfo(){
        initItem();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        MyAdapter adapter = new MyAdapter(items);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

    }


    public void showInfo(){
        showTraceInfo();
        String first  = times.get(0);
        String last = times.get(times.size()-1);
        search_Num.setText(LogisticCode);

    }
    public void showDefaultInfo(){
        search_Num.setText("暂无");
        Toast.makeText(this, "请输入正确信息", Toast.LENGTH_SHORT).show();
    }


}