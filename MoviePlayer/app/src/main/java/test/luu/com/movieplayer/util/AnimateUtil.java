package test.luu.com.movieplayer.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;

import test.luu.com.movieplayer.R;

/**
 * Created by luu trinh on 8/16/2017.
 */


public class AnimateUtil {

    //private int mLastPosition = 0;
    //https://stackoverflow.com/questions/26724964/how-to-animate-recyclerview-items-when-they-appear
    public static void setCardViewFromLeftAnimation(Context context, View viewToAnimate) {
        // If the bound view wasn't previously displayed on screen, it's animated
        //if (position > mLastPosition)
        {
            //Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_from_left);
            viewToAnimate.startAnimation(animation);
            //mLastPosition = position;
        }
    }

    public static void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(200);
        view.startAnimation(anim);
        //AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        //anim.setDuration(FADE_DURATION);
        //view.startAnimation(anim);
    }
}