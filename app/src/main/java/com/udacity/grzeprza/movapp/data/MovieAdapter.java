package com.udacity.grzeprza.movapp.data;

import android.content.Context;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.grzeprza.movapp.network.NetworkUtils;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

/**
 * Created by grzeprza on 19.02.2017.
 */

/**Class handles fetching data into {@link android.widget.GridView}*/
public class MovieAdapter extends ArrayAdapter<Movie> {

    private final String LOG_TAG = this.getClass().getSimpleName();

    private List<Movie> movies;
    private Context context;

    public MovieAdapter(Context context, List<Movie> objects) {
        super(context, 0, objects);
        this.context = context;
        this.movies = objects;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Nullable
    @Override
    public Movie getItem(int position) {
        return movies.get(position);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        if(view == null)
        {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(new GridView.LayoutParams(85,85));
            imageView.setPadding(8,8,8,8);
        }
        else imageView = (ImageView) view;

        URL posterURL = NetworkUtils.ImageHandling.buildImageURL(movies.get(i).getThumbnails(), NetworkUtils.ImageHandling.IMAGE_SIZE.MEDIUM);
        Picasso.with(context).load(posterURL.toString()).into(imageView);
        return imageView;
    }
}
