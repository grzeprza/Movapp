package com.udacity.grzeprza.movapp;

import android.content.Intent;
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
import android.widget.Toast;

import com.udacity.grzeprza.movapp.data.Movie;
import com.udacity.grzeprza.movapp.data.MovieAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static com.udacity.grzeprza.movapp.DetailedMovieActivity.EXTRA_MOVIE_TAG;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.grid_view);

        /*Movie m1 = new Movie();
        m1.setTitle("Title 1");
        m1.setThumbnails(R.drawable.sample_0);
        m1.setOverview("Blah blah blah");
        m1.setReleaseDate(new Date());
        m1.setUserRating(3.5);

        Movie m2 = new Movie();
        m2.setTitle("Title 2");
        m2.setThumbnails(R.drawable.sample_1);
        m2.setOverview("Blah blah blah");
        m2.setReleaseDate(new Date());
        m2.setUserRating(3.5);

        ArrayList<Movie> arrayList = new ArrayList<>();
        arrayList.add(m1);
        arrayList.add(m2);*/

        gridview.setAdapter(new MovieAdapter(this, Arrays.asList(movies)));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                Class detailedMovieActivity = DetailedMovieActivity.class;
                Intent intent = new Intent(getApplicationContext(), detailedMovieActivity);
                Movie movie = (Movie) parent.getItemAtPosition(position);
                intent.putExtra(EXTRA_MOVIE_TAG, movie);
                startActivity(intent);
            }
        });

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
                Toast.makeText(getApplicationContext(),"Feel breeze..",Toast.LENGTH_SHORT).show();
                Log.i(LOG_TAG, "REFRESH ACTION PRESSED");
                return true;

            case R.id.action_sort:
                Toast.makeText(getApplicationContext(), "Nothing to sort..", Toast.LENGTH_SHORT).show();
                Log.i(LOG_TAG, "SORT ACTION PRESSED");
                return true;
            default:
                Log.i(LOG_TAG, "NO ACTION MATCHED");
                return true;
        }
    }

    public static Movie[] movies=
    {
        new Movie("Title 1", R.drawable.sample_0,"Very best movie of all times", 3.5, new Date()),
        new Movie("Title 2", R.drawable.sample_1,"Very best movie of all times", 3.5, new Date()),
        new Movie("Title 3", R.drawable.sample_2,"Very best movie of all times", 3.5, new Date()),
        new Movie("Title 4", R.drawable.sample_3,"Very best movie of all times", 3.5, new Date()),
        new Movie("Title 5", R.drawable.sample_4,"Very best movie of all times", 3.5, new Date()),
        new Movie("Title 6", R.drawable.sample_5,"Very best movie of all times", 3.5, new Date()),
        new Movie("Title 7", R.drawable.sample_6,"Very best movie of all times", 3.5, new Date()),
        new Movie("Title 8", R.drawable.sample_0,"Very best movie of all times", 3.5, new Date())
    };
}
