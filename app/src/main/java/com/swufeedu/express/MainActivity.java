package com.swufeedu.express;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.swufeedu.express.db.DBManager;
import com.swufeedu.express.db.InfoItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{
    private Spinner companySpinner;
    private EditText searchText;
    private RecyclerView recyclerView;
    ListView listView;
    //private InfoAdapter adapter;
    private MainAdapter adapter;
    final String TAG = "MAinActivity";

    private List<InfoItem> infoList = new ArrayList<InfoItem>();//数据库


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.search_list);//列表

        companySpinner = (Spinner) findViewById(R.id.delivery_company_spinner);
        searchText = (EditText) findViewById(R.id.search_text);
        //recyclerView  =(RecyclerView)findViewById(R.id.recycle_view);

    }
    public void submit(View btn) {
        //准备请求参数
        int selectedPosition = companySpinner.getSelectedItemPosition();
        final String companyCode = getResources().getStringArray(R.array.delivery_company)[selectedPosition];
        final String num = searchText.getText().toString();
        Log.i(TAG, "companyCode = "+ companyCode);
        Log.i(TAG, "num = " + num);
        //没有输入快递单号
        if (0 == searchText.getText().length()) {
            Toast.makeText(MainActivity.this, "请输入快递单号", Toast.LENGTH_SHORT).show();
            return;
        }else{
            Toast.makeText(this, "查询成功", Toast.LENGTH_SHORT).show();
        }

        SharedPreferences.Editor editor =  getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("code",companyCode);
        editor.putString("number",num);
        editor.apply();
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);

    }


    public void refresh(View btn) {
        //准备请求参数
        DBManager dbManager = new DBManager(MainActivity.this);
        //dbManager.deleteAll();
        infoList = dbManager.listAll();
        Log.i(TAG,"SUCC");
        adapter = new MainAdapter(MainActivity.this, R.layout.info_item, infoList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(MainActivity.this);
        listView.setOnItemLongClickListener(MainActivity.this);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        adapter = new InfoAdapter(infoList);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL));
//        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ListView list_view =(ListView) parent;
        InfoItem info_item  = (InfoItem) list_view.getItemAtPosition(position);
        Log.i(TAG, "点击事件");
        final String companyCode = info_item.getCurName();
        final String num = info_item.getCurNum();
        Log.i(TAG, "companyCode = "+ companyCode);
        Log.i(TAG, "num = " + num);
        SharedPreferences.Editor editor =  getSharedPreferences("data",MODE_PRIVATE).edit();
        editor.putString("code",companyCode);
        editor.putString("number",num);
        editor.apply();
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
        ListView lview =(ListView) adapterView;
        InfoItem infoitem  = (InfoItem) lview.getItemAtPosition(position);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage("确认删除该历史数据后无法找回")
                .setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(TAG,"onclick:对话框事件处理");
                        DBManager dbManager = new DBManager(MainActivity.this);
//                        String delete_num=infoitem.getCurNum();
//                        dbManager.deleteByNum(delete_num);
                        int delete_num=infoitem.getId();
                        dbManager.delete(delete_num);
                        infoList= dbManager.listAll();
                        Log.i(TAG,"删除成功："+delete_num);
                        adapter = new MainAdapter(MainActivity.this, R.layout.info_item, infoList);
                        listView.setAdapter(adapter);
                        listView.setOnItemLongClickListener(MainActivity.this);
                    }
                }).setNegativeButton("否",null);
        builder.create().show();
        return true;
    }


}