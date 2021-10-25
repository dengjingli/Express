package com.swufeedu.express;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.swufeedu.express.db.InfoItem;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {


    private List<InfoItem> myInfos;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView state;
        TextView num;
        ImageView companyImg;
        public ViewHolder(View view){
            super(view);
            state = (TextView)view.findViewById(R.id.state_text_item);
            num = (TextView)view.findViewById(R.id.search_num_item);
            companyImg = (ImageView)view.findViewById(R.id.company_img_item);

        }
    }


    public InfoAdapter(List<InfoItem>infosList){
        myInfos = infosList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.info_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    //滚动到这里加载
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        InfoItem infoList = myInfos.get(position);
        if(infoList.getCurState().equals("0")) {
            holder.state.setText("暂无轨迹信息");
        }else if(infoList.getCurState().equals("1")){
            holder.state.setText("已揽收");
        }else if(infoList.getCurState().equals("2")){
            holder.state.setText("正在运送");
        }else if(infoList.getCurState().equals("3")){
            holder.state.setText("已签收");
        }else if(infoList.getCurState().equals("4")){
            holder.state.setText("问题件");
        }
        holder.num.setText(infoList.getCurNum());
        
        if(infoList.getCurName().equals("STO")){
            holder.companyImg.setBackgroundResource(R.drawable.sto);
        }else if(infoList.getCurName().equals("YTO")){
            holder.companyImg.setBackgroundResource(R.drawable.yto);
        }else if(infoList.getCurName().equals("HTKY")){
            holder.companyImg.setBackgroundResource(R.drawable.htky);
        }else if(infoList.getCurName().equals("HHTT")){
            holder.companyImg.setBackgroundResource(R.drawable.hhtt);
        }
        ViewHolder itemViewHolder = (ViewHolder) holder;
        ViewGroup.LayoutParams layoutParams = itemViewHolder.itemView.getLayoutParams();
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
    }

    @Override
    public int getItemCount(){
        return myInfos.size();
    }



}
