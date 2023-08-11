package com.example.mobileprogrammingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class orderadapter extends RecyclerView.Adapter<orderadapter.ViewHolder> {
    private final orderadapter.ItemClickListener mitem3;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView r1;
        TextView r2;

        ImageView image2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            r1=(TextView) itemView.findViewById(R.id.r1);
            r2=(TextView) itemView.findViewById(R.id.r2);


            image2=(ImageView) itemView.findViewById(R.id.image2);

        }
    }
    private Context context;
    private List<orders> orderlist;
    public orderadapter(Context c, List<orders> orderlist,orderadapter.ItemClickListener mitem3){
        this.context=c;
        this.orderlist=orderlist;
        this.mitem3=mitem3;
    }
    @NonNull
    @Override
    public orderadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=  LayoutInflater.from(context).inflate(R.layout.activity_orderhistory,parent,false);
        return new orderadapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull orderadapter.ViewHolder holder, int position) {
        orders p=orderlist.get(position);
        holder.r1.setText(p.getR1());
        holder.r2.setText(p.getR2());


        Picasso.get().load(p.getImage2()).into(holder.image2);
        holder.itemView.findViewById(R.id.reorder).setOnClickListener(view -> {
            mitem3.onItemClick(p, position);
        });

    }

    @Override
    public int getItemCount() {
        return orderlist.size();
    }

    public interface ItemClickListener{
        void onItemClick(orders p , int position);
    }

}
