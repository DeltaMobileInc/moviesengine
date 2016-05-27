package app.mobileengine.com.moviesengine.Listener;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by praveen on 4/10/2016.
 */
public class RecyclerviewOnClickListner implements
        RecyclerView.OnItemTouchListener{
    private GestureDetectorCompat mGestureDetector;
    private OnItemClickListner mListener;

    public interface OnItemClickListner{
        void onItemClick(View v, RecyclerView.Adapter adapter, int position);
        void onItemLongClick(View v, int position);
    }

    public RecyclerviewOnClickListner(Context context,final RecyclerView recyclerView,OnItemClickListner listner) {

        mListener = listner;

        mGestureDetector = new GestureDetectorCompat(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(),e.getY());
                if (childView != null && mListener != null) {
                    mListener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
                }
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {


        View childView = rv.findChildViewUnder(e.getX(), e.getY());

        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, rv.getAdapter(), rv.getChildAdapterPosition(childView));

            return true;
        }


        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
