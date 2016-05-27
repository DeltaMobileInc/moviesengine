package app.mobileengine.com.moviesengine.Managers;

/**
 * Created by praveen on 4/10/2016.
 */
public class Constants {
    public static  boolean isloading = false;

    //Api Key
    public static final String API_KEY_VALUE = "80598beb28919f5040a59c8baf2eddd5";
    //DiscoverMovie Uri
    public static final String BASE_Uri = "http://api.themoviedb.org/3/discover/movie?";
    //Movie Detail uri
    public static final String BASE_MOVIE_DETAIL_URI = "http://api.themoviedb.org/3/movie/";
    //Image Uri
    public static final String BASE_IMAGE_URI ="http://image.tmdb.org/t/p/w500";

    //Append paths
    public static final String CREDIT_PATH = "credits";

    //Constants Query Params
    public static final String API_KEY_PARAM = "api_key";
    public static final String PAGE_NO_PARAM = "page";
    public static int   PAGE_NO ;
    public static final String SORT_BY_PARAM = "sort_by";

    //Constants Query Param value
    public static final String SORT_BY_PARAM_POPULARITY_DESC= "popularity.desc";
    public static final String SORT_BY_PARAM_POPULARITY_ASC = "popularity.asc";
    public static final String SORT_BY_PARAM_VOTE_AVERAGE_DESC = "vote_average.desc";
    public static final String SORT_BY_PARAM_VOTE_AVERAGE_ASC = "vote_average.asc";
    public static final String SORT_BY_PARAM_VOTE_COUNT_DESC = "vote_count.desc";
    public static final String SORT_BY_PARAM_VOTE_COUNT_ASC = "vote_count.asc";
    public static final String SORT_BY_PARAM_RELEASE_DATE_DESC = "release_date.desc";
    public static final String SORT_BY_PARAM_RELEASE_DATE_ASC = "release_date.asc";



    //Constants for JSON KEY STRINGS
    public static final String JSON_RESULT_ARRAY_NAME = "results";
    public static final String JSON_POSTERPATH = "poster_path";
    public static final String JSON_BACKDROPPATH = "backdrop_path";
    public static final String JSON_OVERVIEW = "overview";
    public static final String JSON_RELEASE_DATE = "release_date";
    public static final String JSON_MOVIE_ID = "id";
    public static final String JSON_TITLE = "title";
    public static final String JSON_POPULARITY = "popularity";
    public static final String JSON_VIDEO = "video";
    public static final String JSON_VOTE_COUNT = "vote_count";
    public static final String JSON_VOTE_AVG = "vote_average";
    public static final String JSON_GENRE = "genres";
    public static final String JSON_NAME = "name";
    public static final String JSON_LANGUAGES = "spoken_languages";
    public static final String JSON_RUN_TIME ="runtime";
    public static final String JSON_CAST="cast";
    public static final String JSON_CREW="crew";
    public static final String JSON_JOB="job";
    public static final String JSON_JOB_ROLE="Director";

    //Animation Constatnts
    public static int   ANIMATION_DURATION = 250;

    //Parclable key Constants
    public static final String PARCELABLE_OBJECT_KEY = "com.mobileengine.movieengine";


}
