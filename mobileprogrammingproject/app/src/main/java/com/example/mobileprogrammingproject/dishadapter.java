package com.example.mobileprogrammingproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class dishadapter extends RecyclerView.Adapter<dishadapter.ViewHolder> {


    private final ItemClickListener mitem1;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title1;
        TextView title2;
        TextView title3;
        ImageView image1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title1=(TextView) itemView.findViewById(R.id.text1);
            title2=(TextView) itemView.findViewById(R.id.text2);
            title3=(TextView) itemView.findViewById(R.id.text3);

            image1=(ImageView) itemView.findViewById(R.id.image1);

        }
    }
    private Context context;
    private List<dishes>dishlist;
    public dishadapter(Context c, List<dishes> dishesList, ItemClickListener mitem1){
        this.context=c;
        dishlist=dishesList;
        this.mitem1=mitem1;
    }




    @Override
    public dishadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=  LayoutInflater.from(context).inflate(R.layout.activity_dish,parent,false);
        return new dishadapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull dishadapter.ViewHolder holder, int position) {
        dishes p=dishlist.get(position);

        holder.title1.setText(p.getTitle1());
        holder.title2.setText(p.getTitle2());
        holder.title3.setText(p.getTitle3());

        Picasso.get().load(p.getImage1()).into(holder.image1);
        holder.itemView.findViewById(R.id.uio).setOnClickListener(view -> {
            mitem1.onItemClick(p, position);
        });
    }

    @Override
    public int getItemCount() {
        return dishlist.size();
    }

    public interface ItemClickListener{
        void onItemClick(dishes p , int position);
    }

}
