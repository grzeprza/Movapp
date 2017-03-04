package com.udacity.grzeprza.movapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.grzeprza.movapp.data.Movie;
import com.udacity.grzeprza.movapp.network.NetworkUtils;

import java.net.URL;

/**
 * Created by grzeprza on 19.02.2017.
 */
public class DetailedMovieActivity extends AppCompatActivity{

    /**Tag used to {@link Log} information for {@link DetailedMovieActivity}*/
    private final String LOG_TAG = this.getClass().getSimpleName();

    /**String representation of TAG used to recognize {@link Parcelable}*/
    public static final String EXTRA_MOVIE_TAG = "EXTRA_MOVIE_TAG";

    /**Movie title {@link TextView} item reference field declaration*/
    private TextView movieTitle;
    /**Movie release date {@link TextView} item reference field declaration*/
    private TextView movieRelease;
    /**Movie overview {@link TextView} item reference field declaration*/
    private TextView movieDescription;
    /**Movie rating {@link TextView} item reference field declaration*/
    private TextView movieRating;
    /**Movie poster {@link ImageView} item reference field declaration*/
    private ImageView moviePoster;
    /**Movie duration {@link TextView} item reference field declaration*/
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
            movieTitle.setText(movie.getTitle());
            movieRelease.setText(String.valueOf(movie.getReleaseDate().getYear()+1900));
            movieDescription.setText(movie.getOverview());

            // '/10' its the highest available score
            String movieRatingText = String.valueOf(movie.getUserRating()) + "/10";
            movieRating.setText(movieRatingText);

            URL posterURL = NetworkUtils.ImageHandling.buildImageURL(movie.getThumbnails(), NetworkUtils.ImageHandling.IMAGE_SIZE.MEDIUM);
            Picasso.with(getApplicationContext()).load(posterURL.toString()).into(moviePoster);

            // Movie duration it's not in JSON, that's why it is currently unknown ('?')
            movieDuration.setText("? min");
        }
        else Log.e(LOG_TAG, "NO MOVIE PASSED THROUGH PARCABLE");

    }
}
