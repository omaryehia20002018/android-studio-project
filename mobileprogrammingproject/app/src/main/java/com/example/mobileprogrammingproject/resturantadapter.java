package com.example.mobileprogrammingproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class resturantadapter extends RecyclerView.Adapter<resturantadapter.ViewHolder>{


    private final ItemClickListener mitem;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView image;
        ItemClickListener mitem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=(TextView) itemView.findViewById(R.id.text);
            image=(ImageView) itemView.findViewById(R.id.image);
        }
    }
    private Context context;
    private List<resturantrecycle>resturants;
    
    public resturantadapter(Context c, List<resturantrecycle> resturantList , ItemClickListener mitem){
        this.context=c;
        resturants=resturantList;
        this.mitem=mitem;
        
    }
    @Override
    public resturantadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v=  LayoutInflater.from(context).inflate(R.layout.activity_resturant,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
           resturantrecycle p=resturants.get(position);
           holder.title.setText(p.getTitle());
        Picasso.get().load(p.getImage()).into(holder.image);

           holder.itemView.setOnClickListener(view -> {
               mitem.onItemClick(p, position);
           });
}
    @Override
    public int getItemCount() {
        return resturants.size();
    }

    public interface ItemClickListener{
        void onItemClick(resturantrecycle p , int position);
    }
}
