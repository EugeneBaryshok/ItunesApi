package com.example.mi.albumlibrary.screen.main;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;

import com.example.mi.albumlibrary.R;
import com.example.mi.albumlibrary.models.content.Album;
import com.example.mi.albumlibrary.screen.general.LoadingDialog;
import com.example.mi.albumlibrary.screen.general.LoadingView;
import com.example.mi.albumlibrary.screen.tracks.AlbumDetailActivity;
import com.example.mi.albumlibrary.utils.CollapseToolbar;
import com.example.mi.albumlibrary.utils.GridSpacingItemDecoration;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.mi.albumlibrary.utils.Images.loadFromDrawable;

public class MainActivity extends MvpAppCompatActivity implements MainView, SearchView.OnQueryTextListener {

    private Unbinder unbinder;
    private LoadingView mLoadingView;

    private List<Album> albumList;

    private AlbumAdapter albumAdapter;

    @InjectPresenter
    MainPresenter mPresenter;

    @BindView(R.id.album_rv)
    RecyclerView albumsRv;

    @BindView(R.id.backdrop)
    ImageView backdrop;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @BindView(R.id.appbar)
    AppBarLayout appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        CollapseToolbar collapseToolbar = new CollapseToolbar(collapsingToolbar, appbar, getString(R.string.app_name));
        collapseToolbar.initCollapsingToolbar();

        loadFromDrawable(this, backdrop, R.drawable.cover_2);

        mLoadingView = LoadingDialog.view(getSupportFragmentManager());

        mPresenter = new MainPresenter(this);
        mPresenter.init();
    }

    @Override
    public void showAlbums(List<Album> albumList) {
        this.albumList = albumList;
        Collections.sort(albumList);


        //Setup adapter and grid count depends on orientation

        albumAdapter = new AlbumAdapter(this, albumList, mPresenter);
        RecyclerView.LayoutManager mLayoutManager;
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLayoutManager = new GridLayoutManager(this, 2);
            albumsRv.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        } else {
            mLayoutManager = new GridLayoutManager(this, 4);
            albumsRv.addItemDecoration(new GridSpacingItemDecoration(4, dpToPx(10), true));
        }
        albumsRv.setLayoutManager(mLayoutManager);

        albumsRv.setItemAnimator(new DefaultItemAnimator());
        albumsRv.setAdapter(albumAdapter);
    }


    //Launch AlbumDetailActivity
    @Override
    public void showDetails(@NonNull Album album, @NonNull ImageView imageView) {
        AlbumDetailActivity.start(this, album, imageView);
    }

    //Find album by SearchView
    @Override
    public void updateAlbumList(List<Album> newAlbumList) {
        albumAdapter.updateList(newAlbumList);
        if (newAlbumList.size() == 0)
            Toast.makeText(this, "There is no such album", Toast.LENGTH_LONG).show();
    }

    //Add SearchView
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }


    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        mLoadingView.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingView.hideLoading();
    }

    private int dpToPx(int dp) {
        Resources r = this.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mPresenter.searchAlbum(newText, albumList);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

}
