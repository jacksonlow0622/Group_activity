package com.example.final_group_assign_mobile_app;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeFragmentRecyclerViewAdapter extends RecyclerView.Adapter<HomeFragmentRecyclerViewAdapter.ViewHolder> {

    ArrayList<Food> list;
    LayoutInflater inflater;

    public HomeFragmentRecyclerViewAdapter(Context ctx, ArrayList<Food> list){
        this.list = list;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_page_custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeFragmentRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.title.setText(list.get(position).getName());
        //holder.gridIcon.setImageResource(list.get(position).getImage());
        Picasso.get().load(list.get(position).getImage()).into(holder.gridIcon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_grid_search_layout);
            gridIcon = itemView.findViewById(R.id.imageView_grid_search_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Clicked -> " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    //Log.i("v.getContext()", list.get(getAdapterPosition()).foodID);
                    //Intent newActivity = new Intent(v.getContext(), FoodDetails.class);
                    //newActivity.putExtra("foodID", list.get(getAdapterPosition()).foodID);
                    //v.getContext().startActivity(newActivity);
                }
            });
        }
    }
}
