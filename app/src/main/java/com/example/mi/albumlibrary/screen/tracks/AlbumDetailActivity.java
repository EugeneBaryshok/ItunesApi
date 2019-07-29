package com.example.mi.albumlibrary.screen.tracks;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.mi.albumlibrary.R;
import com.example.mi.albumlibrary.models.content.Album;
import com.example.mi.albumlibrary.models.content.Track;
import com.example.mi.albumlibrary.screen.general.LoadingDialog;
import com.example.mi.albumlibrary.screen.general.LoadingView;
import com.example.mi.albumlibrary.screen.main.MainActivity;
import com.example.mi.albumlibrary.utils.CollapseToolbar;
import com.example.mi.albumlibrary.utils.Images;
import com.jgabrielfreitas.core.BlurImageView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AlbumDetailActivity extends MvpAppCompatActivity implements AlbumDetailView {
    private Unbinder unbinder;
    private LoadingView mLoadingView;

    @InjectPresenter
    AlbumDetailPresenter mPresenter;

    @BindView(R.id.detail_album_cover)
    BlurImageView AlbumCoverImg;

    @BindView(R.id.detail_album_img)
    ImageView AlbumThumbnailImg;

    @BindView(R.id.album_name)
    TextView AlbumName;

    @BindView(R.id.album_year)
    TextView AlbumYear;

    @BindView(R.id.album_artist)
    TextView AlbumArtist;

    @BindView(R.id.tracks_rv)
    RecyclerView TracksRV;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.appbar)
    AppBarLayout appbar;

    public static void start(@NonNull Activity activity, @NonNull Album album, ImageView movieImageView) {
        Intent intent = new Intent(activity, AlbumDetailActivity.class);
        intent.putExtra("id", album.getmCollectionId());
        ActivityOptions options = null;

        //Intent activity with image animation
        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                options = ActivityOptions.makeSceneTransitionAnimation(activity,
                        movieImageView, "sharedName");
            }
        } catch (Exception e) {
            Log.e("ERROR_TAG", "error on: " + e.getMessage());
        }

        activity.startActivity(intent, Objects.requireNonNull(options).toBundle());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        mPresenter = new AlbumDetailPresenter(this);

        int id = Objects.requireNonNull(getIntent().getExtras()).getInt("id");
        mPresenter.init(id);

        //Add animation on albumCover
        AlbumCoverImg.setAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_animation));

    }

    @Override
    public void setFieldsOnInit(@NonNull Album album) {
            String albumTitle = album.getmName();
            Date albumReleaseDate = album.getmReleaseDate();


            //Init collapsing toolbar on scroll
            CollapseToolbar collapseToolbar = new CollapseToolbar(collapsingToolbar, appbar, albumTitle);
            collapseToolbar.initCollapsingToolbar();

            AlbumArtist.setText(album.getmArtistName());
            AlbumName.setText(albumTitle);


            //Get year of release from date
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(albumReleaseDate);
            int year = calendar.get(Calendar.YEAR);
            AlbumYear.setText(String.valueOf(year));


            //load cover and thumbnail images
            Images.loadMovie(AlbumThumbnailImg, album.getmAlbumImage(), Images.WIDTH_100);
            Images.loadMovie(AlbumCoverImg, album.getmAlbumImage(), Images.WIDTH_780);
            AlbumCoverImg.setBlur(18);
    }


    //Show list of tracks
    @Override
    public void showTracks(List<Track> trackList) {
        TracksAdapter tracksAdapter = new TracksAdapter(this, trackList);
        TracksRV.setAdapter(tracksAdapter);
        TracksRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }


    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
