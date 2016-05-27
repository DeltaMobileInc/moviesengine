package app.mobileengine.com.moviesengine;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;

import app.mobileengine.com.moviesengine.Listener.RecyclerviewOnClickListner;
import app.mobileengine.com.moviesengine.Managers.Constants;
import app.mobileengine.com.moviesengine.Managers.FetchMovieCompleteDetail;
import app.mobileengine.com.moviesengine.Managers.HelperManager;
import app.mobileengine.com.moviesengine.MoviesObjects.Movies;
import app.mobileengine.com.moviesengine.adapters.RecyclerAdapter;


/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesEngineFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    private final String LOG_TAG = MoviesEngine.class.getSimpleName();


    GridView gridview;
    RecyclerView.Adapter mAdapter;
    RecyclerView mRecyclerView;
    ArrayList<Movies> moviesArrayList = new ArrayList<>();
    SharedPreferences sortPref;

    public MoviesEngineFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Toast.makeText(getActivity().getApplicationContext(), "onResume ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        // Toast.makeText(getActivity().getApplicationContext(), "onStop ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPause() {
        super.onPause();
        //Toast.makeText(getActivity().getApplicationContext(), "onPause ", Toast.LENGTH_SHORT).show();
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recyclerview_fragment_layout, container, false);
        sortPref = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        sortPref.registerOnSharedPreferenceChangeListener(this);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerGridView);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mAdapter = new RecyclerAdapter(getActivity().getApplicationContext(), moviesArrayList);
        //Get Data From Api
        // new FetchMovies(getActivity().getApplicationContext(), mRecyclerView, mAdapter,moviesArrayList).execute();
        HelperManager.loadDataFromApi(getActivity(), getActivity().getApplicationContext(), mRecyclerView,
                mAdapter, moviesArrayList,HelperManager.getSortByPrefValue(getActivity().getApplicationContext()) );
        mRecyclerView.setAdapter(mAdapter);


        mRecyclerView.addOnItemTouchListener(new RecyclerviewOnClickListner(getActivity().getApplicationContext(), mRecyclerView, new RecyclerviewOnClickListner.OnItemClickListner() {
            @Override
            public void onItemClick(View v, RecyclerView.Adapter adapter, int position) {
                // Toast.makeText(getActivity().getApplicationContext(), "onItemClick Clicked"+v.getHeight()+" "+v.getWidth(), Toast.LENGTH_SHORT).show();
                Intent mIntent = new Intent(getActivity().getApplicationContext(), MoviesDetails.class);
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mIntent.putExtra(Constants.PARCELABLE_OBJECT_KEY,moviesArrayList.get(position));
                new FetchMovieCompleteDetail(getActivity().getApplicationContext(),moviesArrayList.get(position),mIntent).execute();


                //startActivity(mIntent);
            }

            @Override
            public void onItemLongClick(View v, int position) {
                // Toast.makeText(getActivity().getApplicationContext(), "onItemLongClick Clicked", Toast.LENGTH_SHORT).show();
            }
        }));

        //ScrollListener to get more data from api #pagination
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                if (dy > 0) {
                    if (!Constants.isloading && gridLayoutManager.findLastVisibleItemPosition() > gridLayoutManager.getItemCount() - 5) {
                     /*   Toast.makeText(getActivity().getApplicationContext(),"onScrolled "+gridLayoutManager.findLastVisibleItemPosition()
                                +" "+gridLayoutManager.getItemCount(),Toast.LENGTH_SHORT).show();*/
                        Constants.isloading = true;
                        HelperManager.loadDataFromApi(getActivity(), getActivity().getApplicationContext(),
                                mRecyclerView, mAdapter, moviesArrayList,
                                HelperManager.getSortByPrefValue(getActivity().getApplicationContext()));
                    }

                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                //Toast.makeText(getActivity().getApplicationContext(),"onScrollStateChanged",Toast.LENGTH_SHORT).show();
            }
        });


        return rootView;
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //Toast.makeText(getActivity().getApplicationContext(),"onSharedPreferenceChanged",Toast.LENGTH_SHORT).show();
       moviesArrayList.clear();
        HelperManager.loadDataFromApi(getActivity(), getActivity().getApplicationContext(), mRecyclerView,
                mAdapter, moviesArrayList, HelperManager.getSortByPrefValue(getActivity().getApplicationContext()));
        //mAdapter.notifyDataSetChanged();
    }
}
