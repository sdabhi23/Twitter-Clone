package com.shrey.task1sample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;

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
        ImageView rowConImg = convertView.findViewById(R.id.conImg);
        ImageView rowProfImg = convertView.findViewById(R.id.profImg);

        TextView txtComment = convertView.findViewById(R.id.txtComment);
        TextView txtRT = convertView.findViewById(R.id.txtRT);
        TextView txtLike = convertView.findViewById(R.id.txtLike);

        rowName.setText(tweet.getName());
        rowHandle.setText(tweet.getHandle());
        rowMin.setText(tweet.getMinutes());
        rowContent.setText(tweet.getContent());
        rowConImg.setImageResource(tweet.getConImg());
        rowProfImg.setImageResource(tweet.getProf());

        txtComment.setText(String.valueOf(tweet.getComment()));
        txtComment.setText(String.valueOf(tweet.getRt()));
        txtComment.setText(String.valueOf(tweet.getLike()));

        return convertView;
    }
}
