package com.example.mi.albumlibrary.utils;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.BaseAdapter;

import com.arellomobile.mvp.MvpDelegate;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 26.01.2016
 * Time: 17:26
 *
 * @author Yuri Shmakov
 */
public abstract class MvpBaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {
	private MvpDelegate<? extends MvpBaseRecyclerViewAdapter> mMvpDelegate;
	private MvpDelegate<?> mParentDelegate;
	private String mChildId;


	public MvpBaseRecyclerViewAdapter(MvpDelegate<?> parentDelegate, String childId) {
		mParentDelegate = parentDelegate;
		mChildId = childId;

		getMvpDelegate().onCreate();
	}

	public MvpDelegate getMvpDelegate() {
		if (mMvpDelegate == null) {
			mMvpDelegate = new MvpDelegate<>(this);
			mMvpDelegate.setParentDelegate(mParentDelegate, mChildId);

		}
		return mMvpDelegate;
	}
	@CallSuper
	@Override
	public void onBindViewHolder(VH holder, int position) {
		holder.itemView.setTag(position);
	}

}
