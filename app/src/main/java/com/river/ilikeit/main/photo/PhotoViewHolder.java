package com.river.ilikeit.main.photo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.river.ilikeit.R;

public class PhotoViewHolder extends RecyclerView.ViewHolder {
    public TextView tvContent;
    public TextView tvAuthor;
    public ImageView ivPhoto;

    public PhotoViewHolder(View view) {
        super(view);
        tvAuthor = (TextView) view.findViewById(R.id.tvAuthor);
        tvContent = (TextView) view.findViewById(R.id.tvContent);
        ivPhoto = (ImageView) view.findViewById(R.id.ivPhoto);
    }
}
