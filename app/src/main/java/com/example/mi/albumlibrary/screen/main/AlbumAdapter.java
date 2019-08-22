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

import com.arellomobile.mvp.MvpDelegate;
import com.example.mi.albumlibrary.R;
import com.example.mi.albumlibrary.models.content.Album;
import com.example.mi.albumlibrary.utils.Images;
import com.example.mi.albumlibrary.utils.MvpBaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumAdapter extends MvpBaseRecyclerViewAdapter<AlbumAdapter.AlbumHolder, Album> {

    Context context;
    List<Album> mData;
    AlbumItemClickListener albumItemClickListener;


//    public AlbumAdapter(@NonNull List<Album> mData) {
//        super(mData);
//    }

    public AlbumAdapter(MvpDelegate<?> parentDelegate,  AlbumItemClickListener listener) {
        super(parentDelegate, String.valueOf(0));
        mData = new ArrayList<>();
        this.albumItemClickListener = listener;
    }

    public void setAlbums(List<Album> albums) {
        mData = new ArrayList<>(albums);
    }


//    public AlbumAdapter(Context context, List<Album> mData) {
//        this.context = context;
//        this.mData = mData;
//    }
//    public AlbumAdapter(Context context, List<Album> mData,  AlbumItemClickListener listener) {
//        this.context = context;
//        this.mData = mData;
//        this.albumItemClickListener = listener;
//    }

    @NonNull
    @Override
    public AlbumHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        return new AlbumHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.album_item, viewGroup, false));
//        View view = LayoutInflater.from(context).inflate(R.layout.album_item, viewGroup, false);
////        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumHolder albumHolder, int position) {
        super.onBindViewHolder(albumHolder, position);
        Album album = getItem(position);
        albumHolder.bind(album);
    }
    @NonNull
    public Album getItem(int position) {
        return mData.get(position);
    }
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//        viewHolder.AlbumTitle.setText(mData.get(i).getmName());
//        viewHolder.AlbumTracksCount.setText(mData.get(i).getmTrackCount()+" songs");
//        Images.loadMovie(myViewHolder.AlbumImg, mData.get(i), Images.WIDTH_100);
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull AlbumAdapter.MyViewHolder myViewHolder, int i) {
//
//
//    }

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
    public class AlbumHolder extends RecyclerView.ViewHolder {
        private MvpDelegate mMvpDelegate;
        private Album mAlbum;
        @BindView(R.id.album_title)
        TextView AlbumTitle;
        @BindView(R.id.tracks_count)
        TextView AlbumTracksCount;
        @BindView(R.id.album_thumbnail)
        ImageView AlbumImg;

        public AlbumHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
        public void bind(@NonNull Album album) {
            if (getMvpDelegate() != null) {
                getMvpDelegate().onSaveInstanceState();
                getMvpDelegate().onDetach();
                getMvpDelegate().onDestroyView();
                mMvpDelegate = null;
            }
            mAlbum = album;
            getMvpDelegate().onCreate();
            getMvpDelegate().onAttach();

            AlbumTitle.setText(album.getmName());
            AlbumTracksCount.setText(album.getmTrackCount()+" songs");
            Images.loadMovie(AlbumImg, album, Images.WIDTH_100);
//            itemView.setOnClickListener(v -> mRepositoryLikesPresenter.toggleLike(repository.getId()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    albumItemClickListener.onAlbumClick(mData.get(getAdapterPosition()),AlbumImg);
                }
            });
        }
        MvpDelegate getMvpDelegate() {
            if (mAlbum == null) {
                return null;
            }

            if (mMvpDelegate == null) {
                mMvpDelegate = new MvpDelegate<>(this);
                mMvpDelegate.setParentDelegate(AlbumAdapter.this.getMvpDelegate(), String.valueOf(mAlbum.getmCollectionId()));

            }
            return mMvpDelegate;
        }
    }

    public void updateList(List<Album> albumList)
    {
      mData = new ArrayList<>();
      mData.addAll(albumList);
      notifyDataSetChanged();
    }
}
