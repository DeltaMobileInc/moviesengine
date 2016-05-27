package app.mobileengine.com.moviesengine.Managers;

import android.content.Context;
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
import app.mobileengine.com.moviesengine.R;

/**
 * Created by praveen on 4/16/2016.
 */
public class FetchCredits extends AsyncTask<String,Void,Void> {
    private final String LOG_TAG = FetchCredits.class.getSimpleName();

    private Context context;
    private View cRootView;
    private Movies cMovie;
    private TextView vCastTextView,vCrewTextView;

    public FetchCredits(Context context, Movies movie, View view) {
        this.context = context;
        cRootView = view;
        cMovie = movie;
    }


    private void setMovieCreditJsonResponse(String cResponse){

        String cCast ="";
        String cCrew ="";
        JSONObject eachCrewObject,eachCastObject;

        try {
            JSONObject rootObject = new JSONObject(cResponse);
            JSONArray castArrayObject = rootObject.getJSONArray(Constants.JSON_CAST);

            for (int i = 0; i<castArrayObject.length();i++)
            {
                 eachCastObject = null;
                 eachCastObject = castArrayObject.getJSONObject(i);
                 cCast = cCast+eachCastObject.optString(Constants.JSON_NAME)+System.getProperty("line.separator");

            }
            JSONArray crewArrayObject = rootObject.getJSONArray(Constants.JSON_CREW);
            for (int i = 0;i< crewArrayObject.length();i++)
            {
                 eachCrewObject = null;
                 eachCrewObject = crewArrayObject.getJSONObject(i);
                if(eachCrewObject.optString(Constants.JSON_JOB ).equalsIgnoreCase(Constants.JSON_JOB_ROLE))
                {
                    cCrew = cCrew + eachCrewObject.optString(Constants.JSON_NAME)+System.getProperty("line.separator");
                }

            }

            cMovie.setmCast(HelperManager.getTextValue(cCast));
            cMovie.setmCrew(HelperManager.getTextValue(cCrew));

        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Error parsing ="+LOG_TAG,e.getMessage());
        }




    }





    @Override
    protected Void doInBackground(String... params) {
        //Connection
        HttpURLConnection httpURLConnection = null;
        InputStream inputStreamReader;
        BufferedReader bufferedReader = null;
        String dMoviesCrewStr;
        StringBuffer buffer = new StringBuffer();

        //Building Uri for api call
        Uri queryUri = Uri.parse(Constants.BASE_MOVIE_DETAIL_URI).buildUpon()
                .appendPath(params[0])//appending path movie id /id
                .appendPath(Constants.CREDIT_PATH)
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
            dMoviesCrewStr = buffer.toString();
            //Passing json response to parse the data
            setMovieCreditJsonResponse(dMoviesCrewStr);
            Log.d(LOG_TAG, "Results String : " + dMoviesCrewStr);


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
        vCastTextView = (TextView)cRootView.findViewById(R.id.vCast);
        vCastTextView.setText(cMovie.getmCast());
        vCrewTextView = (TextView)cRootView.findViewById(R.id.vDirector);
        vCrewTextView.setText(cMovie.getmCrew());

    }
}
