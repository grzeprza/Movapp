package com.udacity.grzeprza.movapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.grzeprza.movapp.data.Movie;

/**
 * Created by grzeprza on 19.02.2017.
 */
public class DetailedMovieActivity extends AppCompatActivity{

    private final String LOG_TAG = this.getClass().getSimpleName();

    public static final String EXTRA_MOVIE_TAG = "EXTRA_MOVIE_TAG";

    private TextView movieTitle;
    private TextView movieRelease;
    private TextView movieDescription;
    private TextView movieRating;
    private ImageView moviePoster;
    private TextView movieDuration;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_movie);

        movieTitle = (TextView) findViewById(R.id.textView_movTitle);
        movieRelease = (TextView) findViewById(R.id.textView_movRelease);
        movieDescription = (TextView) findViewById(R.id.textView_movDescription);
        movieDuration = (TextView) findViewById(R.id.textView_movDuration);
        movieRating = (TextView) findViewById(R.id.textView_movRating);
        moviePoster = (ImageView) findViewById(R.id.imageView_movPoster);

        Movie movie = (Movie) getIntent().getParcelableExtra(EXTRA_MOVIE_TAG);
        if(movie != null)
        {
            //Toast.makeText(getApplicationContext(),movie.toString(),Toast.LENGTH_SHORT).show();
            movieTitle.setText(movie.getTitle());
            movieRelease.setText(String.valueOf(movie.getReleaseDate().getYear()+1900));
            movieDescription.setText(movie.getOverview());
            String movieRatingText = String.valueOf(movie.getUserRating()) + "/10";
            movieRating.setText(movieRatingText);
            moviePoster.setImageResource(movie.getThumbnails());
            movieDuration.setText("120min");
        }
        else Log.e(LOG_TAG, "NO MOVIE PASSED THROUGH PARCABLE");

    }
}
