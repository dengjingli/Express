package com.swufeedu.express;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Spinner companySpinner;
    private EditText searchText;
    final String TAG = "MAinActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        companySpinner = (Spinner) findViewById(R.id.delivery_company_spinner);
        searchText = (EditText) findViewById(R.id.search_text);

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
}