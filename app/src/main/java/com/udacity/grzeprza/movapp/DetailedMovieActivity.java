package com.udacity.grzeprza.movapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

/**
 * Created by grzeprza on 19.02.2017.
 */
public class DetailedMovieActivity extends AppCompatActivity{

    public static final String EXTRA_MOVIE_TAG = "EXTRA_MOVIE_TAG";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_movie);
    }
}
