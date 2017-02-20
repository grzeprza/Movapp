package com.udacity.grzeprza.movapp.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by grzeprza on 19.02.2017.
 */

/**Representation of movie object, due to requirements*/
public class Movie implements Parcelable{

    /**Variable stores movie title*/
    private String title;

    /**Variable stoes movie thumbnails*/
    private Integer thumbnails;

    /**Variable stores movie overview, called also plot synopsis*/
    private String overview;

    /**Variable stores users rating of movie, called also vote avarage*/
    private Double userRating;

    /**Variable stores release date*/
    private Date releaseDate;

    /**Default movie constructor*/
    public Movie() {
    }

    /**Movie constructor*/
    public Movie(String title, Integer thumbnails, String overview, Double userRating, Date releaseDate) {
        this.title = title;
        this.thumbnails = thumbnails;
        this.overview = overview;
        this.userRating = userRating;
        this.releaseDate = releaseDate;
    }

    /**Parcelable Movie constructor*/
    public Movie(Parcel in) {
        this.title = in.readString();
        this.thumbnails = in.readInt();
        this.overview = in.readString();
        this.userRating = in.readDouble();
        this.releaseDate = new Date(in.readLong());

    }



    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(title);
        dest.writeInt(thumbnails);
        dest.writeString(overview);
        dest.writeDouble(userRating);
        dest.writeLong(releaseDate.getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    static final Parcelable.Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public String toString() {
        return this.getTitle() + " Picture nr " + this.getThumbnails();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Integer thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
