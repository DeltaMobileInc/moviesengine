package app.mobileengine.com.moviesengine.Managers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import app.mobileengine.com.moviesengine.MoviesObjects.Movies;

/**
 * Created by praveen on 4/15/2016.
 */
public class FetchMovieCompleteDetail extends AsyncTask<Void,Void,Void> {
    private static final String LOG_TAG = FetchMovieCompleteDetail.class.getSimpleName();

    private Context context;
    private Movies dMovies;
    private View dLayoutView;
    private TextView vGenreTextView,vRuntimetextView,vLangTextView;
    private Intent dIntent;
    public FetchMovieCompleteDetail(Context context, Movies movies, Intent intent) {
        this.context = context;
        dMovies = movies;
        dIntent = intent;
    }

    private void setDetailedJsonResponseInMoviesObject(String dResults){
        String dGenre = "";
        String dLang = "";
        try {
            JSONObject rootObject = new JSONObject(dResults);
            JSONArray genreArrayObject = rootObject.getJSONArray(Constants.JSON_GENRE);

            for (int i = 0; i<genreArrayObject.length();i++)
            {
                JSONObject eachGenreObject = genreArrayObject.getJSONObject(i);
                dGenre = dGenre+eachGenreObject.optString(Constants.JSON_NAME)+System.getProperty("line.separator");

            }
            JSONArray langArrayObject = rootObject.getJSONArray(Constants.JSON_LANGUAGES);
            for (int i = 0;i< langArrayObject.length();i++)
            {
                JSONObject eachLangObject = langArrayObject.getJSONObject(i);
                dLang = dLang + eachLangObject.optString(Constants.JSON_NAME)+System.getProperty("line.separator");
            }
            //Log.d(LOG_TAG,"Genre = "+genre);

            //Setting new Values in movies object
            dMovies.setmRunTime(HelperManager.getRuntimeInHours(rootObject.optString(Constants.JSON_RUN_TIME)));
            dMovies.setmLanguage(HelperManager.getTextValue(dLang));
            dMovies.setmGenre(HelperManager.getTextValue(dGenre));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error parsing ="+LOG_TAG,e.getMessage());
        }


    }

    @Override
    protected Void doInBackground(Void... params) {

        //Connection
        HttpURLConnection httpURLConnection = null;
        InputStream inputStreamReader;
        BufferedReader bufferedReader = null;
        String dMoviesResultStr;
        StringBuffer buffer = new StringBuffer();

        //Building Uri for api call
        Uri queryUri = Uri.parse(Constants.BASE_MOVIE_DETAIL_URI).buildUpon()
                .appendPath(dMovies.getmMovieId())//appending path movie id /id
                .appendQueryParameter(Constants.API_KEY_PARAM, Constants.API_KEY_VALUE)
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
            dMoviesResultStr = buffer.toString();
            //Passing json response to parse the data
            setDetailedJsonResponseInMoviesObject(dMoviesResultStr);
            Log.d(LOG_TAG, "Results String : " + dMoviesResultStr);


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

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
      /*  vGenreTextView= (TextView) dLayoutView.findViewById(R.id.vGenre);
        vGenreTextView.setText(dMovies.getmGenre());
        vRuntimetextView = (TextView)dLayoutView.findViewById(R.id.vRuntime);
        vRuntimetextView.setText(dMovies.getmRunTime() );
        vLangTextView = (TextView)dLayoutView.findViewById(R.id.vLang);
        vLangTextView.setText(dMovies.getmLanguage());*/
        context.startActivity(dIntent);

    }
}
