package com.example.mobileprogrammingproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class cartadapter extends RecyclerView.Adapter<cartadapter.ViewHolder>  {
  private final ItemClickListener mitem2;



    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView order;
        TextView money;
        Button order1;

        ImageView dishimg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            order=(TextView) itemView.findViewById(R.id.meat);
            money=(TextView) itemView.findViewById(R.id.money);
            order1=(Button) itemView.findViewById(R.id.additem);

            dishimg=(ImageView) itemView.findViewById(R.id.dishimage);
        }
    }

    private Context context;
    private List<carts> cartlist;
    public cartadapter(Context c, List<carts> cartList, cartadapter.ItemClickListener mitem2){
        this.context=c;
        cartlist=cartList;
        this.mitem2=mitem2;
    }
    @NonNull
    @Override
    public cartadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=  LayoutInflater.from(context).inflate(R.layout.activity_cart,parent,false);
        return new cartadapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        carts p=cartlist.get(position);
        holder.order.setText(p.getOrder());
        holder.money.setText(p.getMoney());

        Picasso.get().load(p.getDishimg()).into(holder.dishimg);
        /*
        holder.itemView.findViewById(R.id.additem).setOnClickListener(view -> {
            mitem2.onItemClick(p);
        });*/

        holder.itemView.setOnClickListener(view -> {
            mitem2.onItemClick(p, position);
        });

    }

    @Override
    public int getItemCount() {
        return  cartlist.size();
    }

    public interface ItemClickListener {
        void onItemClick(carts p , int position);
    }

}
