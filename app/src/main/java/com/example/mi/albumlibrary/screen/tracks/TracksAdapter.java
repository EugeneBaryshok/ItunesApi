package com.example.mi.albumlibrary.screen.tracks;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mi.albumlibrary.R;
import com.example.mi.albumlibrary.models.content.Track;
import com.example.mi.albumlibrary.utils.Images;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.MyViewHolder> {

    private Context context;
    private List<Track> mData;

    public TracksAdapter(Context context, List<Track> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.track_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.TrackTitle.setText(mData.get(i).getTrackName());
        myViewHolder.TrackNumber.setText(String.valueOf(i+1));
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.track_name)
        TextView TrackTitle;

        @BindView(R.id.track_number)
        TextView TrackNumber;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
