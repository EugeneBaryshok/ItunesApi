package com.example.mi.albumlibrary.models.response;

import com.example.mi.albumlibrary.models.content.Track;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TrackResponse {
    @SerializedName("resultCount")

    private Integer resultCount;
    @SerializedName("results")
    private List<Track> tracks = null;
    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }


}
