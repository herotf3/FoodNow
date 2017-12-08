package com.example.asus.foodnow.UICustomView;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;

/**
 * Created by ASUS on 12/8/2017.
 */

public class SizedHeightScaleAnimation extends ScaleAnimation {

    private float fromHeight;
    private float toHeight;
    float originHeight;
    private View viewToScale;

    public SizedHeightScaleAnimation(View viewToScale, float fromY, float toY) {
        super(1, 1, fromY, toY);
        init(viewToScale, fromY, toY);
    }

    public SizedHeightScaleAnimation(View viewToScale, float fromY, float toY, float pivotX, float pivotY, float originHeight) {
        super(1, 1, fromY, toY, pivotX, pivotY);
        init(viewToScale, fromY, toY);
    }

    public SizedHeightScaleAnimation(View viewToScale, float fromY, float toY,float originHeight, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
        super(1, 1, fromY, toY, pivotXType, pivotXValue, pivotYType, pivotYValue);
        this.originHeight=originHeight;
        init(viewToScale, fromY, toY);
    }

    private void init(View viewToScale, float fromY, float toY) {

        this.viewToScale = viewToScale;
        viewToScale.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (fromY<toY){
            //grow
            fromHeight = this.originHeight;
            toHeight = viewToScale.getMeasuredHeight() * toY;
        }else
        {
            //shrink
            fromHeight = viewToScale.getMeasuredHeight();
            toHeight = originHeight;
        }

    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);

        viewToScale.getLayoutParams().height = (int) (toHeight * interpolatedTime);
        viewToScale.requestLayout();
    }
}
