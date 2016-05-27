package app.mobileengine.com.moviesengine.Managers;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import app.mobileengine.com.moviesengine.MoviesObjects.Movies;

/**
 * Created by praveen on 4/10/2016.
 */


public class FetchMovies extends AsyncTask<String, Void, ArrayList<Movies>> {

    private final String LOG_TAG = FetchMovies.class.getSimpleName();
    private ArrayList<Movies> mMovieArrayList;
    private Movies mMovie;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private Activity mActivity;


    public FetchMovies(Context c) {

    }

    public FetchMovies(Activity activity,Context applicationContext, RecyclerView recycleView,
                       RecyclerView.Adapter adapter, ArrayList<Movies> movieArrayList) {
        mActivity = activity;
        mContext = applicationContext;
        mRecyclerView = recycleView;
        mAdapter = adapter;
        mMovieArrayList = movieArrayList;
    }

    /**
     * Put the json data in movie object
     *
     * @return
     */
    private ArrayList<Movies> putJsonDataInMoveObject(String jsonResult) {

        ArrayList<Movies> allMovies = new ArrayList<Movies>();
        JSONObject resultHolderObject;

        try {
            JSONObject jsonRootObject = new JSONObject(jsonResult);
            JSONArray resultsArray = jsonRootObject.getJSONArray(Constants.JSON_RESULT_ARRAY_NAME);

            for (int counter = 0; counter < resultsArray.length(); counter++) {
                resultHolderObject = null;
                resultHolderObject = resultsArray.getJSONObject(counter);
                mMovie = new Movies(resultHolderObject.getString(Constants.JSON_POSTERPATH),resultHolderObject.optString(Constants.JSON_BACKDROPPATH), resultHolderObject.optString(Constants.JSON_OVERVIEW),
                        resultHolderObject.optString(Constants.JSON_RELEASE_DATE), resultHolderObject.optString(Constants.JSON_MOVIE_ID),
                        resultHolderObject.optString(Constants.JSON_TITLE), resultHolderObject.optString(Constants.JSON_VIDEO),
                        resultHolderObject.optString(Constants.JSON_VOTE_COUNT), resultHolderObject.optString(Constants.JSON_VOTE_AVG),
                        "false",resultHolderObject.optString(Constants.JSON_POPULARITY), mContext);
                allMovies.add(mMovie);
                //Log.d(LOG_TAG, "poster Path : " + mVoteAverage);
            }

        } catch (JSONException e) {
            Log.e(LOG_TAG, "Error from parsing json results " + e);
        }


        return allMovies;
    }


    @Override
    protected ArrayList<Movies> doInBackground(String... params) {
        //Connection
        HttpURLConnection httpURLConnection = null;
        InputStream inputStreamReader;
        BufferedReader bufferedReader = null;
        String moviesResultStr;
        StringBuffer buffer = new StringBuffer();
        if(Constants.isloading){
            Constants.PAGE_NO = Constants.PAGE_NO+1;
        }else if(!Constants.isloading) {
            Constants.PAGE_NO = 1;
        }
        else
        {
            Toast.makeText(mContext,"Error in is Loading Api call ",Toast.LENGTH_SHORT).show();
        }
        //Building Uri for api call
        Uri queryUri = Uri.parse(Constants.BASE_Uri).buildUpon()
                .appendQueryParameter(Constants.PAGE_NO_PARAM,Integer.toString(Constants.PAGE_NO))
                .appendQueryParameter(Constants.API_KEY_PARAM, Constants.API_KEY_VALUE)
                .appendQueryParameter(Constants.SORT_BY_PARAM,params[0])
                .build();

        try {
            URL url = new URL(queryUri.toString());
            //http connection setup
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            inputStreamReader = httpURLConnection.getInputStream();
            if (inputStreamReader == null) {
                return null;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStreamReader));
            if (bufferedReader == null) {
                return null;
            }
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }
            //jsonresult
            moviesResultStr = buffer.toString();

            //Log.d(LOG_TAG, "Results String : " + moviesResultStr);


        } catch (IOException ioException) {
            Log.e(LOG_TAG, "error from doInbackground Task " + ioException.getMessage());
            ioException.printStackTrace();

            return null;
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "Error closing buferReader", e);
                }
            }
        }


        return putJsonDataInMoveObject(moviesResultStr);
    }

    @Override
    protected void onPostExecute(ArrayList<Movies> movies) {
        super.onPostExecute(movies);
        if(Constants.isloading){
            Constants.isloading = false;
        }else{
        }
        mMovieArrayList.addAll(movies);
        //loading sorted data in adapter
        mAdapter.notifyDataSetChanged();

    }
}








