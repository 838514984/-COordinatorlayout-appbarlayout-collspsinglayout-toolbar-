package com.example.administrator.mycoolapsinglayoutwithrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2017/7/18 0018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {
    private List<String> mdatas;
    private Context context;
    private MyListener myListener;
    public MyAdapter(List<String> mdatas ,Context context,MyListener myListener){
        this.mdatas=mdatas;
        this.context=context;
        this.myListener=myListener;
    }
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.settext(R.id.tv,mdatas.get(position));
        if (myListener!=null){
            holder.contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myListener.onClick(v);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public  static class VH extends RecyclerView.ViewHolder{
        SparseArray sparseArray=new SparseArray();
        View contentView;
        public VH(View itemView) {
            super(itemView);
            this.contentView=itemView;
        }

        public void settext(int id,String msg){
            View view= (View) sparseArray.get(id);
            if (view==null){
                view=contentView.findViewById(id);
                sparseArray.put(id,view);
            }
            ((TextView)view).setText(msg);
        }
    }

    interface MyListener{
        void onClick(View view);
    }
}
