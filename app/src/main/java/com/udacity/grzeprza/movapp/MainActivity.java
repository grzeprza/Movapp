package com.udacity.grzeprza.movapp;

import android.content.Intent;
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

import com.udacity.grzeprza.movapp.data.MovieAdapter;

import static com.udacity.grzeprza.movapp.DetailedMovieActivity.EXTRA_MOVIE_TAG;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.grid_view);
        gridview.setAdapter(new MovieAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Class detailedMovieActivity = DetailedMovieActivity.class;
                Intent intent = new Intent(getApplicationContext(), detailedMovieActivity);
                intent.putExtra(EXTRA_MOVIE_TAG,parent.getSelectedItemId());
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
}
