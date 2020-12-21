package com.example.tried;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> itemLabels;
    ArrayList<Bitmap> images;

    public MyAdapter(Context context, List<String> itemLabels, ArrayList<Bitmap> images){
        this.itemLabels = itemLabels;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recently_added_display, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(itemLabels.get(position));
        holder.imageView.setImageBitmap(images.get((position)));
    }

    @Override
    public int getItemCount() {
        return itemLabels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.productNameDisplay);
            imageView = itemView.findViewById(R.id.imageDisplayer);
        }
    }
}

