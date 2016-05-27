package app.mobileengine.com.moviesengine.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.mobileengine.com.moviesengine.Managers.Constants;
import app.mobileengine.com.moviesengine.MoviesObjects.Movies;
import app.mobileengine.com.moviesengine.R;

/**
 * Created by praveen on 4/8/2016.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Movies> mMovie;
    private Context mContext;
    //suitable construct for type of dataset

    public RecyclerAdapter(Context c ,ArrayList<Movies> mArray)
    {
        mMovie = mArray;
        mContext = c;
    }
    //View holder to hold the view for of the each item in the recycler view
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private ImageButton likeImage;
        private TextView avgratingText;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.movieVideoThumb);
            likeImage = (ImageButton)itemView.findViewById(R.id.imageratingButton);
            avgratingText =(TextView)itemView.findViewById(R.id.avgratingTextView);

        }
    }



    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        Movies bindMovie = mMovie.get(position);
        int width= mContext.getResources().getDisplayMetrics().widthPixels;
        Picasso.with(mContext)
                .load(Constants.BASE_IMAGE_URI+bindMovie.getmPosterPath())
                .resize(width / 2, width/2)
                .centerInside()
                .placeholder(R.drawable.film_reel_placeholder)
                .error(R.drawable.film_reel_placeholder)
                .into(holder.imageView);
        holder.likeImage.setBackgroundResource(R.drawable.rating);
        holder.avgratingText.setText(bindMovie.getmVoteAverage());
        //Setting animation on views
        //TODO animation is messing the grid layout look into that later
        //AnimationManager.setScaleAnimation(holder.itemView);
        //AnimationManager.setFadeAnimation(holder.itemView);

    }



    @Override
    public int getItemCount() {
        return mMovie.size();
    }
}
