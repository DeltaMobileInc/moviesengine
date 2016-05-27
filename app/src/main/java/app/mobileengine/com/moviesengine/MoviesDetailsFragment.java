package app.mobileengine.com.moviesengine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.mobileengine.com.moviesengine.Managers.Constants;
import app.mobileengine.com.moviesengine.Managers.FetchCredits;
import app.mobileengine.com.moviesengine.Managers.HelperManager;
import app.mobileengine.com.moviesengine.MoviesObjects.Movies;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesDetailsFragment extends Fragment {
    private static final String LOG_TAG = MoviesDetailsFragment.class.getSimpleName();
    private Movies dMovie;

    //Views
    private ImageView dVideoThumbnailView;
    private TextView dMovieTitle,dReleaseDate,dAverageVote,mSynopsis,dGenreTextView,dRuntimetextView,dLangTextView,dLikePercentTextView,dVoteTextView;
    public MoviesDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        dMovie = getActivity().getIntent().getParcelableExtra(Constants.PARCELABLE_OBJECT_KEY);


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.movies_details_fragment_layout, container, false);
        //get Cast and crew info
        new FetchCredits(getActivity().getApplicationContext(),dMovie,rootView).execute(dMovie.getmMovieId());

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle(dMovie.getmTitle());


        dVideoThumbnailView = (ImageView)rootView.findViewById(R.id.movieVideoThumbnail);
        Picasso.with(getActivity().getApplicationContext())
                .load(Constants.BASE_IMAGE_URI+dMovie.getmBackDropPath())
                .placeholder(R.drawable.film_reel_placeholder)
                .error(R.drawable.film_reel_placeholder)
                .into(dVideoThumbnailView) ;


        dMovieTitle = (TextView)rootView.findViewById(R.id.movieName);
        dMovieTitle.setText(dMovie.getmTitle());
        dReleaseDate = (TextView)rootView.findViewById(R.id.movieReleasedate);
        dReleaseDate.setText(dMovie.getmReleaseDate());
        dAverageVote = (TextView)rootView.findViewById(R.id.vAvgvote);
        dAverageVote.setText(dMovie.getmVoteAverage());

        dGenreTextView= (TextView) rootView.findViewById(R.id.vGenre);
        dGenreTextView.setText(dMovie.getmGenre());
        dRuntimetextView = (TextView)rootView.findViewById(R.id.vRuntime);
        dRuntimetextView.setText(dMovie.getmRunTime() );
        dLangTextView = (TextView)rootView.findViewById(R.id.vLang);
        dLangTextView.setText(dMovie.getmLanguage());
        dLikePercentTextView = (TextView)rootView.findViewById(R.id.vLikePercentage);
        dLikePercentTextView.setText(HelperManager.getLikePercentage(dMovie.getmVoteAverage()));
        dVoteTextView = (TextView)rootView.findViewById(R.id.vVoteCount);
        dVoteTextView.setText(HelperManager.getTextValue(dMovie.getmVoteCount()+" Votes"));

        mSynopsis = (TextView)rootView.findViewById(R.id.vOverview);
        mSynopsis.setText(dMovie.getmOverview());



        return rootView;
    }

}
