package com.example.asus.foodnow.UICustomView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import android.widget.ImageView;

/**
 * Created by ASUS on 12/7/2017.
 */

public class ImageExpanable extends android.support.v7.widget.AppCompatImageView{
    int height;
    private boolean zoom=false;
    float originHeight;

    public void init(){
        this.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        originHeight=this.getMeasuredHeight();
        final Animation grow=createExpansion(this);
        grow.setDuration(1000);
        grow.setFillAfter(true);
        final Animation shrink=createCollapse(this);
        shrink.setDuration(1000);
        shrink.setFillAfter(true);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!zoom){
                    zoom=true;
                    startAnimation(grow);
                }
                else{
                    zoom=false;
                    startAnimation(shrink);
                }
            }
        });
    }

    public ImageExpanable(Context context) {
        super(context);
        zoom=false;
        init();
    }

    public ImageExpanable(Context context, AttributeSet attrs) {
        super(context, attrs);
        zoom=false;
        init();
    }

    public ImageExpanable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.zoom = false;
        init();
    }

    private  Animation createExpansion(View view) {
        return new SizedHeightScaleAnimation(view, 1f, 1.3f,originHeight,
                Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 0f);
    }

    private Animation createCollapse(View view) {
        return new SizedHeightScaleAnimation(view,1.3f, 1f,originHeight,
                Animation.RELATIVE_TO_SELF,0f,
                Animation.RELATIVE_TO_SELF,0f);
    }
}

