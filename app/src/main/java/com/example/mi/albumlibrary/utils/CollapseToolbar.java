package com.example.mi.albumlibrary.utils;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;


public class CollapseToolbar {
    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private String text;

    public CollapseToolbar(@NonNull CollapsingToolbarLayout collapsingToolbar, @NonNull AppBarLayout appBarLayout, @NonNull String text) {
        this.collapsingToolbar = collapsingToolbar;
        this.appBarLayout = appBarLayout;
        this.text = text;
    }

    public void initCollapsingToolbar()
    {
        collapsingToolbar.setTitle(" ");
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.BaseOnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(text);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
}
