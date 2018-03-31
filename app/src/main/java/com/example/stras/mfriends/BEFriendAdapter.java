package com.example.stras.mfriends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BEFriendAdapter extends ArrayAdapter<BEFriend> {

    private int[] colors = new int[]{0x00FFFFFF, 0x30808080};

    public BEFriendAdapter(Context context, ArrayList<BEFriend> beFriends) {
        super(context, 0, beFriends);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        BEFriend beFriend = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_friend, parent, false);
        }

        convertView.setMinimumHeight(180);

        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
        tvName.setText(beFriend.getName());

        int colorPos = position % colors.length;
        convertView.setBackgroundColor(colors[colorPos]);

        return convertView;
    }
}
