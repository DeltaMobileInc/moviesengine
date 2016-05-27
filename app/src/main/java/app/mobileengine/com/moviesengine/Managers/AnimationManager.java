package app.mobileengine.com.moviesengine.Managers;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import java.util.LinkedList;

/**
 * Created by praveen on 4/11/2016.
 */
public class AnimationManager {

    LinkedList<String> testlist = new LinkedList<String>();



    public static void setScaleAnimation(View view)
    {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(Constants.ANIMATION_DURATION);
        view.startAnimation(scaleAnimation);


    }
    public static void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(Constants.ANIMATION_DURATION);
        view.startAnimation(anim);
    }
}
