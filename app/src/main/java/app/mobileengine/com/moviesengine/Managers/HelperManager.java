package app.mobileengine.com.moviesengine.Managers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Comparator;

import app.mobileengine.com.moviesengine.MoviesObjects.Movies;
import app.mobileengine.com.moviesengine.R;

/**
 * Created by praveen on 4/11/2016.
 */
public class HelperManager {

    //Load more data from Api
    public static void loadDataFromApi(Activity activity, Context c, RecyclerView rv, RecyclerView.Adapter adapter,
                                       ArrayList<Movies> arrayList, String sortParam) {
        new FetchMovies(activity, c, rv, adapter, arrayList).execute(sortParam);

    }

    /**
     * Load sorted data as per sort request in Recycler Adapter
     *
     * @param context
     * @return
     */
    public static String getSortByPrefValue(Context context) {
        SharedPreferences sortPref = PreferenceManager.getDefaultSharedPreferences(context);
        String sortPrefValue = sortPref.getString(context.getString(R.string.pref_sort_by_key)
                , context.getString(R.string.pref_sort_by_defaultvalue));


        if (sortPrefValue !=null && sortPrefValue.equalsIgnoreCase(context.getString(R.string.pref_sort_value_1))) {
            return Constants.SORT_BY_PARAM_POPULARITY_DESC;
        }  if (sortPrefValue !=null &&sortPrefValue.equalsIgnoreCase(context.getString(R.string.pref_sort_value_2))) {
            return Constants.SORT_BY_PARAM_VOTE_AVERAGE_DESC;
        }  if (sortPrefValue !=null && sortPrefValue.equalsIgnoreCase(context.getString(R.string.pref_sort_value_3))) {
            return Constants.SORT_BY_PARAM_VOTE_COUNT_DESC;
        } /* if (sortPrefValue !=null && sortPrefValue.equalsIgnoreCase(context.getString(R.string.pref_sort_value_4))) {
            return Constants.SORT_BY_PARAM_RELEASE_DATE_DESC;
        }*/else {
            return Constants.SORT_BY_PARAM_POPULARITY_DESC;
        }
    }

    /**
     *
     * @param time
     * @return
     */
    public static String getRuntimeInHours(String time)
    {
        if(!TextUtils.isEmpty(time))
        {
            return Integer.toString(Integer.parseInt(time)/60)+" hrs "+Integer.toString(Integer.parseInt(time)%60)+" mins";
        }
        else{
            return "NA";
        }

    }

    /**
     *
     * @param avgVote
     * @return
     */
    public static String getLikePercentage(String avgVote){

       ;

        if(!TextUtils.isEmpty(avgVote)){
           return  Double.toString( Double.parseDouble(avgVote)*10)+"%";
        }
       else {
            return "NA";
        }
    }


    public static String getTextValue(String string){
        if(!TextUtils.isEmpty(string)) {
            return string.trim();
        }

        return "NA";
    }

    /**
     * Sort by comparator
     */
    public static class PopularityComparator implements Comparator<Movies> {

        @Override
        public int compare(Movies lhs, Movies rhs) {
            return rhs.getmPopularity().compareTo(lhs.getmPopularity());
        }
    }

    public static class AverageVoteComparator implements Comparator<Movies> {

        @Override
        public int compare(Movies lhs, Movies rhs) {
            return rhs.getmVoteAverage().compareTo(lhs.getmVoteAverage());
        }
    }

    public static class VoteCountComparator implements Comparator<Movies> {

        @Override
        public int compare(Movies lhs, Movies rhs) {
            return rhs.getmVoteCount().compareTo(lhs.getmVoteCount());
        }
    }


}
