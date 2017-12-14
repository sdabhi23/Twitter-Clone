package com.shrey.task1sample;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Shrey on 12/3/2017.
 */

public class TweetAdapter extends ArrayAdapter<Tweet> {

    public TweetAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public TweetAdapter(@NonNull Context context, int resource, ArrayList<Tweet> tweets) {
        super(context, resource, tweets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Tweet tweet = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tweet_row, parent, false);
        }

        TextView rowName = convertView.findViewById(R.id.name);
        TextView rowHandle = convertView.findViewById(R.id.handle);
        TextView rowMin = convertView.findViewById(R.id.min);
        TextView rowContent = convertView.findViewById(R.id.content);
        TextView rowConImg = convertView.findViewById(R.id.conImg);
        ImageView rowProfImg = convertView.findViewById(R.id.profImg);

        TextView txtComment = convertView.findViewById(R.id.txtComment);
        TextView txtRT = convertView.findViewById(R.id.txtRT);
        TextView txtLike = convertView.findViewById(R.id.txtLike);

        rowName.setText(tweet.getName());
        rowHandle.setText(tweet.getHandle());
        rowMin.setText(tweet.getMinutes());
        rowContent.setText(tweet.getContent());
        rowProfImg.setImageResource(tweet.getProf());

        txtComment.setText(String.valueOf(tweet.getComment()));
        txtRT.setText(String.valueOf(tweet.getRt()));
        txtLike.setText(String.valueOf(tweet.getLike()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            rowConImg.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(tweet.getConImg())));
        }

        return convertView;
    }
}
