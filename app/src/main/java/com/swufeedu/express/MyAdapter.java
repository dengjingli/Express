package com.swufeedu.express;
/***
 * 参考老师的代码
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


    private List<Item> myItems;
    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView station;
        TextView time;
        public ViewHolder(View view){
            super(view);
            station = (TextView)view.findViewById(R.id.accept_station);
            time = (TextView)view.findViewById(R.id.accept_time);

        }
    }


    public MyAdapter(List<Item>itemsList){
        myItems = itemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent , int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Item itemList = myItems.get(position);
        holder.station.setText(itemList.getStation());
        holder.time.setText(itemList.getTime());
        ViewHolder itemViewHolder = (ViewHolder) holder;
        ViewGroup.LayoutParams layoutParams = itemViewHolder.itemView.getLayoutParams();
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
    }


    @Override
    public  int  getItemCount(){
        return myItems.size();
    }

}
