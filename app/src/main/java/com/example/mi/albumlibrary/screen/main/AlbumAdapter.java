package com.example.mi.albumlibrary.screen.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mi.albumlibrary.R;
import com.example.mi.albumlibrary.models.content.Album;
import com.example.mi.albumlibrary.utils.Images;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    Context context;
    List<Album> mData;
    AlbumItemClickListener albumItemClickListener;


    public AlbumAdapter(Context context, List<Album> mData) {
        this.context = context;
        this.mData = mData;
    }
    public AlbumAdapter(Context context, List<Album> mData,  AlbumItemClickListener listener) {
        this.context = context;
        this.mData = mData;
        this.albumItemClickListener = listener;
    }

    @NonNull
    @Override
    public AlbumAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.album_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.MyViewHolder myViewHolder, int i) {

        myViewHolder.AlbumTitle.setText(mData.get(i).getmName());
        myViewHolder.AlbumTracksCount.setText(mData.get(i).getmTrackCount()+" songs");
        Images.loadMovie(myViewHolder.AlbumImg, mData.get(i), Images.WIDTH_100);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.album_title)
        TextView AlbumTitle;
        @BindView(R.id.tracks_count)
        TextView AlbumTracksCount;
        @BindView(R.id.album_thumbnail)
        ImageView AlbumImg;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    albumItemClickListener.onAlbumClick(mData.get(getAdapterPosition()),AlbumImg);
                }
            });
        }
    }

    public void updateList(List<Album> albumList)
    {
      mData = new ArrayList<>();
      mData.addAll(albumList);
      notifyDataSetChanged();
    }
}
