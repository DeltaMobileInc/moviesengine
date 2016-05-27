package app.mobileengine.com.moviesengine.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.mobileengine.com.moviesengine.MoviesObjects.Movies;
import app.mobileengine.com.moviesengine.R;

/**
 * Created by praveen on 4/3/2016.
 */
public class MoviesAdapter extends ArrayAdapter<Movies> {

    private ArrayList<Movies> mMovieList;
    private Context mContext;
    private LayoutInflater mInflator;
    private ImageView imageView;
    public MoviesAdapter(Context context, ArrayList<Movies> movieList) {
        super(context,0, movieList);
        mContext = context;
        mMovieList = movieList;
        mInflator = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movies movie =getItem(position);
        imageView = null;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            convertView = mInflator.inflate(R.layout.grid_item,parent,false);
            imageView =(ImageView)convertView.findViewById(R.id.grideImageView);

            /*imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);*/
        } else {
            imageView = (ImageView) convertView;
        }
        int width= mContext.getResources().getDisplayMetrics().widthPixels;
        //using picason image library
        Picasso.with(mContext)
                .load("http://image.tmdb.org/t/p/w185" + movie.getmPosterPath())
                .centerCrop().resize(width / 2, width/2)
                .into(imageView);
        //imageView.setImageResource(mThumbIds[position]);
        return imageView;

    }


}
