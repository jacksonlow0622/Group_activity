package com.example.final_group_assign_mobile_app;

import android.content.Context;
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
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    ArrayList<Food> list;
    ArrayList<String> foodTypeList;
    ArrayList<String> imageList;
//    List<String> titles;
//    List<Integer> images;
    LayoutInflater inflater;

    public SearchAdapter(Context ctx, ArrayList<Food> list, ArrayList<String> foodTypeList, ArrayList<String> imageList){ /*List<String> titles, List<Integer> images*/
//        this.titles = titles;
//        this.images = images;
        this.list = list;
        this.foodTypeList = foodTypeList;
        this.imageList = imageList;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_page_custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.title.setText(titles.get(position));
        //holder.gridIcon.setImageResource(images.get(position));

        holder.title.setText(foodTypeList.get(position));
        Log.i("getPosition", foodTypeList.get(position));
        Log.i("Imagelist: ", imageList.get(position));
        Picasso.get().load(imageList.get(position)).into(holder.gridIcon); //load(list.get(position).getImage()).into(holder.gridIcon)
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
                }
            });
        }
    }
}
