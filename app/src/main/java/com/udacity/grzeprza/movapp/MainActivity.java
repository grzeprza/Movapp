package com.udacity.grzeprza.movapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.grzeprza.movapp.data.Movie;
import com.udacity.grzeprza.movapp.data.MovieAdapter;
import com.udacity.grzeprza.movapp.network.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static com.udacity.grzeprza.movapp.DetailedMovieActivity.EXTRA_MOVIE_TAG;

public class MainActivity extends AppCompatActivity {

    /**String TAG variable to {@link Log} information*/
    private final String LOG_TAG = this.getClass().getSimpleName();

    /**Displays all movies. After clicking on selected movie user is directed to {@link DetailedMovieActivity}*/
    private GridView gridview;

    /**Displays information about not having access to internet*/
    private TextView textViewNoInternet;

    /**Displays progress bar during processing content from the internet*/
    private ProgressBar progressBar;

    /**Variable changes sorting order. By most popular or top rated*/
    private boolean sortPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sortPopular = true;

        gridview = (GridView) findViewById(R.id.grid_view);
        textViewNoInternet = (TextView) findViewById(R.id.textView_noInternetInfo);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        uploadContent();
        sortPopular= !sortPopular;
    }

    /**Method handles shown content, which depends on user access to the internet*/
    private void uploadContent()
    {
        if(!isOnline())
        {
            Log.i(LOG_TAG,"Internet access available");
            textViewNoInternet.setVisibility(View.INVISIBLE);
            gridview.setVisibility(View.VISIBLE);

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                    Class detailedMovieActivity = DetailedMovieActivity.class;
                    Intent intent = new Intent(getApplicationContext(), detailedMovieActivity);
                    Movie movie = (Movie) parent.getItemAtPosition(position);
                    intent.putExtra(EXTRA_MOVIE_TAG, movie);
                    startActivity(intent);
                }
            });
            new MovieDBQueryTask().execute(NetworkUtils.buildMoviesUrl(true));
        }
        else
        {
            Log.i(LOG_TAG, "Internet access UNavailable");
            textViewNoInternet.setVisibility(View.VISIBLE);
            gridview.setVisibility(View.INVISIBLE);
        }
    }

    /**Method checks whether user is connected to the internet*/
    private boolean isOnline() {
        ConnectivityManager conMan = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMan.getActiveNetworkInfo();
        return !(netInfo != null && netInfo.isConnectedOrConnecting());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId)
        {
            case R.id.action_refresh:
                Log.i(LOG_TAG, "REFRESH ACTION PRESSED");
                uploadContent();
                return true;

            case R.id.action_sort:
                new MovieDBQueryTask().execute(NetworkUtils.buildMoviesUrl(sortPopular));
                sortPopular= !sortPopular;
                Log.i(LOG_TAG, "SORT ACTION PRESSED");
                return true;
            default:
                Log.i(LOG_TAG, "NO ACTION MATCHED");
                return true;
        }
    }

    /**Handles downloading internet resource asynchronously*/
    public class MovieDBQueryTask extends AsyncTask<URL, Void, String>
    {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String queryResult = null;
            try
            {
                queryResult = NetworkUtils.getResponseFromUrl(searchUrl);

            }catch (IOException e)
            {
                e.printStackTrace();
            }

            return queryResult;
        }

        @Override
        protected void onPostExecute(String queryResult) {
            if(queryResult != null && !queryResult.equals(""))
            {
                try {
                    JSONObject jsonObject = new JSONObject(queryResult);
                    JSONArray moviesJsonArray =  jsonObject.getJSONArray(NetworkUtils.JsonConstants.RESULTS);
                    ArrayList<Movie> movArrayList = new ArrayList<>();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Movie movie = null;
                    try
                    {
                        for(int i=0; i < moviesJsonArray.length(); i++)
                        {
                            movie = new Movie();
                            movie.setTitle(moviesJsonArray.getJSONObject(i).getString(NetworkUtils.JsonConstants.ORIGINAL_TITLE));
                            movie.setOverview(moviesJsonArray.getJSONObject(i).getString(NetworkUtils.JsonConstants.OVERVIEW));
                            movie.setUserRating(Double.valueOf(moviesJsonArray.getJSONObject(i).getString(NetworkUtils.JsonConstants.VOTE_AVG)));
                            movie.setReleaseDate(sdf.parse(moviesJsonArray.getJSONObject(i).getString(NetworkUtils.JsonConstants.RELEASE_DATE)));
                            movie.setThumbnails(moviesJsonArray.getJSONObject(i).getString(NetworkUtils.JsonConstants.POSTER_PATH).substring(1)); //TODO: CHANGE THUMBNAILS TO STRING, WHEN UPLOADING USE NETWORK UTILS METHOD
                            movArrayList.add(movie);
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    gridview.setAdapter(new MovieAdapter(getApplicationContext(), movArrayList));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else Log.e(LOG_TAG, "# NO ERROR QUERY RESULT #");

            progressBar.setVisibility(View.INVISIBLE);
        }

    }
}
