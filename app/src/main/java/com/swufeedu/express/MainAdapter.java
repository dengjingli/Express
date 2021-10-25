package com.swufeedu.express;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.swufeedu.express.db.InfoItem;

import java.util.HashMap;
import java.util.List;

public class MainAdapter extends ArrayAdapter {
    int recourceId;

    TextView state;
    TextView num;
    ImageView companyImg;
    public MainAdapter(@NonNull Context context, int resource, @NonNull List<InfoItem> objects) {
        super(context, resource, objects);
        recourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        InfoItem infoList  = (InfoItem) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(recourceId, parent, false);
        state = (TextView)view.findViewById(R.id.state_text_item);
        num = (TextView)view.findViewById(R.id.search_num_item);
        companyImg = (ImageView)view.findViewById(R.id.company_img_item);
        if(infoList.getCurState().equals("0")) {
            state.setText("暂无轨迹信息");
        }else if(infoList.getCurState().equals("1")){
            state.setText("已揽收");
        }else if(infoList.getCurState().equals("2")){
            state.setText("正在运送");
        }else if(infoList.getCurState().equals("3")){
            state.setText("已签收");
        }else if(infoList.getCurState().equals("4")){
            state.setText("问题件");
        }
        num.setText(infoList.getCurNum());

        if(infoList.getCurName().equals("STO")){
            companyImg.setBackgroundResource(R.drawable.sto);
        }else if(infoList.getCurName().equals("YTO")){
            companyImg.setBackgroundResource(R.drawable.yto);
        }else if(infoList.getCurName().equals("HTKY")){
            companyImg.setBackgroundResource(R.drawable.htky);
        }else if(infoList.getCurName().equals("HHTT")){
            companyImg.setBackgroundResource(R.drawable.hhtt);
        }

        return view;
    }

}
