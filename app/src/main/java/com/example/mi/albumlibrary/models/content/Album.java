package com.example.mi.albumlibrary.models.content;

import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.arch.persistence.room.Entity;


import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmObject;

@Entity
public class Album extends RealmObject implements Parcelable, Comparable<Album> {
    @SerializedName("wrapperType")
    private String mWrapperType;

    @PrimaryKey
    @SerializedName("collectionId")
    private int mCollectionId;

    @SerializedName("artistName")
    private String mArtistName;

    @SerializedName("releaseDate")
    private Date mReleaseDate;

    @SerializedName("collectionName")
    private String mName;

    @SerializedName("artistViewUrl")
    private String mArtistUrl;

    @SerializedName("collectionViewUrl")
    private String mAlbumUrl;

    @SerializedName("artworkUrl100")
    private String mAlbumImage;

 @SerializedName("trackCount")
    private String mTrackCount;

    public Album() {
    }


    protected Album(Parcel in) {
        mWrapperType = in.readString();
        mCollectionId = in.readInt();
        mArtistName = in.readString();
        mName = in.readString();
        mArtistUrl = in.readString();
        mAlbumUrl = in.readString();
        mAlbumImage = in.readString();
        mReleaseDate = (java.util.Date) in.readSerializable();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mWrapperType);
        dest.writeInt(mCollectionId);
        dest.writeString(mArtistName);
        dest.writeString(mName);
        dest.writeString(mArtistUrl);
        dest.writeString(mAlbumUrl);
        dest.writeString(mAlbumImage);
        dest.writeSerializable(mReleaseDate);
    }

    public String getmWrapperType() {
        return mWrapperType;
    }

    public void setmWrapperType(String mWrapperType) {
        this.mWrapperType = mWrapperType;
    }

    public int getmCollectionId() {
        return mCollectionId;
    }

    public void setmCollectionId(int mCollectionId) {
        this.mCollectionId = mCollectionId;
    }

    public String getmArtistName() {
        return mArtistName;
    }

    public Date getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(Date mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public void setmArtistName(String mArtistName) {
        this.mArtistName = mArtistName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmArtistUrl() {
        return mArtistUrl;
    }

    public void setmArtistUrl(String mArtistUrl) {
        this.mArtistUrl = mArtistUrl;
    }

    public String getmAlbumUrl() {
        return mAlbumUrl;
    }

    public void setmAlbumUrl(String mAlbumUrl) {
        this.mAlbumUrl = mAlbumUrl;
    }

    public String getmAlbumImage() {
        return mAlbumImage;
    }

    public String getmTrackCount() {
        return mTrackCount;
    }

    public void setmTrackCount(String mTrackCount) {
        this.mTrackCount = mTrackCount;
    }

    public void setmAlbumImage(String mAlbumImage) {
        this.mAlbumImage = mAlbumImage;
    }

    public static Album getAlbumById(int id) {
        Album album;
        Realm realm = Realm.getDefaultInstance();
        Realm instance = null;
        try {
            instance = Realm.getDefaultInstance();
            realm.beginTransaction();
            album = realm.where(Album.class)
                    .equalTo("mCollectionId", id)
                    .findFirst();
            if (album == null) {
                realm.cancelTransaction();
            }

            realm.commitTransaction();
        } catch (Throwable e) {
            if (instance != null && instance.isInTransaction()) {
                instance.cancelTransaction();
            }
            throw e;
        } finally {
            if (instance != null) {
                instance.close();
            }
        }
        return album;
    }

    @Override
    public int compareTo(Album o) {
        if (getmName() == null || o.getmName() == null) {
            return 0;
        }
        return getmName().compareTo(o.getmName());
    }
}
