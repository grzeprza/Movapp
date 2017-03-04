package com.udacity.grzeprza.movapp.network;

/**
 * Created by grzeprza on 20.02.2017.
 */

import android.net.Uri;

import com.udacity.grzeprza.movapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**Enables easy access to themoviedb.org API*/
public class NetworkUtils {

    /**Base URL required to contstruct query to MOVIEDB*/
    final static String MOVIEDB_BASE_URL = "http://api.themoviedb.org/3/movie";

    /**Variable used to get most popular movies by parameter*/
    final static String PARAM_POPULAR = "popular";

    /**Variable uset to get top rated movies by parameter*/
    final static String PARAM_TOP_RATED = "top_rated";

    /**Query parameter required to set API KEY*/
    final static String API_KEY = "api_key";

    /**{@param popular} flag whether to recive popular or top rated movies*/
    public final static URL buildMoviesUrl(boolean popular)
    {
        String movies= popular ? PARAM_POPULAR : PARAM_TOP_RATED;
        Uri buildUri = Uri.parse(MOVIEDB_BASE_URL)
                .buildUpon()
                .appendPath(movies)
                //TODO: provide own API KEY below as second parameter
                .appendQueryParameter(API_KEY, "YOU_API_KEY_HERE")
                .build();

        URL url = null;
        try
        {
            url = new URL(buildUri.toString());
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        return url;
    }

    /**Method responsible for getting json content for the given {@param url}*/
    public static String getResponseFromUrl(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        try
        {
            InputStream in = httpURLConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput)
            {
                return scanner.next();
            } else return null;
        }
        finally
        {
            httpURLConnection.disconnect();
        }
    }

    /**Inner class enabling easy access to remote images*/
    public static class ImageHandling
    {
        /**Basic {@link URL} for image resources due to JSON response*/
        final static String MOVIEDB_IMAGE_BASE_URL = "http://image.tmdb.org/t/p/";

        /**Build ready URL for given image. {@param poster} is name of the image and {@param} size refers to {@link IMAGE_SIZE} enum}*/
        public static URL buildImageURL( String poster, IMAGE_SIZE size)
        {
            Uri uri = Uri.parse(MOVIEDB_IMAGE_BASE_URL)
                    .buildUpon()
                    .appendPath(size.getValue())
                    .appendPath(poster)
                    .build();

            URL url = null;

                try {
                url = new URL(uri.toString());
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return url;
        }

        /**Enum values refering to few available image sizes*/
        public enum IMAGE_SIZE
        {
            SMALL("w92"), MEDIUM("w154"), LARGE("w342");

            private String size;
            IMAGE_SIZE(String size){this.size = size;}
            public String getValue(){return this.size;}

        }
    }

    /**Contains all required JSON attributes used in app*/
    public static class JsonConstants {
        public static final String RESULTS = "results";
        public static final String POSTER_PATH = "poster_path";
        public static final String OVERVIEW = "overview";
        public static final String RELEASE_DATE = "release_date";
        public static final String ORIGINAL_TITLE = "original_title";
        public static final String VOTE_AVG = "vote_average";
    }
}
