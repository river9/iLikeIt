package com.river.ilikeit.main.photo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.river.ilikeit.Constant;
import com.river.ilikeit.R;
import com.river.ilikeit.Utility;
import com.river.ilikeit.photo.PhotoDetailActivity;

import java.util.List;

public class PhotosFragmentAdapter extends RecyclerView.Adapter<PhotoViewHolder> {

    private Context context;
    private List<PhotoInfo> photoList;

    public PhotosFragmentAdapter(Context context, List<PhotoInfo> photoList) {
        this.context = context;
        this.photoList = photoList;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, final int position) {
        PhotoInfo photo = photoList.get(position);
        holder.tvContent.setText(photo.content);
        holder.tvAuthor.setText(photo.author);
        holder.ivPhoto.setOnClickListener(new OnPhotoClickListener(position));
        Utility.getInstance(context).displayImage(holder.ivPhoto, photo.link);
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    private class OnPhotoClickListener implements View.OnClickListener {
        private int position;

        public OnPhotoClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Bundle args = new Bundle();
            args.putInt(Constant.ARG_SECTION_NUMBER, position);

            Intent intent = new Intent(context, PhotoDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtras(args);

            context.startActivity(intent);
        }
    }
}