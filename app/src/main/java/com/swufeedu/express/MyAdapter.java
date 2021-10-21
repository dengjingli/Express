package com.swufeedu.express;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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


    //滚动到这里加载
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Item loadTrace = myItems.get(position);
        holder.station.setText(loadTrace.getStation());
        holder.time.setText(loadTrace.getTime());
    }


    @Override
    public  int  getItemCount(){
        return myItems.size();
    }

}
