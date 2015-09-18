package com.river.ilikeit.main.contact;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.siyamed.shapeimageview.CircularImageView;
import com.river.ilikeit.R;
import com.river.ilikeit.Utility;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactsFragmentAdapter extends BaseAdapter {

    private List<ContactInfo> contacts;

    public ContactsFragmentAdapter(List<ContactInfo> contacts) {
        this.contacts = contacts;
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public ContactInfo getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContactViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
            holder = new ContactViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ContactViewHolder) convertView.getTag();
        }

        ContactInfo contact = contacts.get(position);
        holder.tvAuthor.setText(contact.name);
        Utility.getInstance(parent.getContext()).displayImage(holder.ivPhoto, contact.photoUrl);

        return convertView;
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        public TextView tvAuthor;
        public CircularImageView ivPhoto;

        public ContactViewHolder(View view) {
            super(view);
            tvAuthor = (TextView) view.findViewById(R.id.tvAuthor);
            ivPhoto = (CircularImageView) view.findViewById(R.id.ivAuthor);
        }
    }
}
