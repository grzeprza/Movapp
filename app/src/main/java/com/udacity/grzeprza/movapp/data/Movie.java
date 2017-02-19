package com.udacity.grzeprza.movapp.data;

import android.provider.MediaStore;

import java.util.Date;

/**
 * Created by grzeprza on 19.02.2017.
 */

/**Representation of movie object, due to requirements*/
public class Movie {

    /**Variable stores movie title*/
    private String title;

    /**Variable stoes movie thumbnails*/
    private MediaStore.Images.Thumbnails thumbnails;

    /**Variable stores movie overview, called also plot synopsis*/
    private String overview;

    /**Variable stores users rating of movie, called also vote avarage*/
    private Float userRating;

    /**Variable stores release date*/
    private Date releaseDate;

    public Movie(String title, MediaStore.Images.Thumbnails thumbnails, String overview, Float userRating, Date releaseDate) {
        this.title = title;
        this.thumbnails = thumbnails;
        this.overview = overview;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MediaStore.Images.Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(MediaStore.Images.Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Float getUserRating() {
        return userRating;
    }

    public void setUserRating(Float userRating) {
        this.userRating = userRating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
