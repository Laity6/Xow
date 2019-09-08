package com.ol.xow.base.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.ol.xow.R;

public class RateTextCircularProgressBar extends FrameLayout implements CircularProgressBar.OnProgressChangeListener {

    private CircularProgressBar mCircularProgressBar;
    private TextView mRateText;
    private ImageView mImageView;

    public RateTextCircularProgressBar(Context context) {
        super(context);
        init();
    }

    public RateTextCircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mCircularProgressBar = new CircularProgressBar(getContext());
        this.addView(mCircularProgressBar);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER;
        mCircularProgressBar.setLayoutParams(lp);

        mRateText = new TextView(getContext());
        this.addView(mRateText);
        mRateText.setLayoutParams(lp);
        mRateText.setGravity(Gravity.CENTER);
        mRateText.setTextColor(Color.parseColor("#08a1ef"));
        mRateText.setTextSize(10);
        mRateText.setVisibility(GONE);
        mRateText.setClickable(false);

        LayoutParams layoutParamsImage = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParamsImage.gravity = Gravity.CENTER;
        mImageView = new ImageView(getContext());
        this.addView(mImageView);
        mImageView.setLayoutParams(layoutParamsImage);
        mImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        mImageView.setClickable(false);
        mImageView.setImageResource(R.drawable.ic_baojing);
        mCircularProgressBar.setOnProgressChangeListener(this);
    }


    /**
     * 设置最大值
     */
    public void setMax(int max) {
        mCircularProgressBar.setMax(max);
    }

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        mCircularProgressBar.setProgress(progress);
    }

    /**
     * 得到 CircularProgressBar 对象，用来设置其他的一些属性
     *
     * @return
     */
    public CircularProgressBar getCircularProgressBar() {
        return mCircularProgressBar;
    }

    /**
     * 设置图层
     *
     * @param drawableId
     */
    public void setStateImage(int drawableId) {
        mImageView.setImageResource(drawableId);
    }

    public void setStateImageColor(int color){
        mImageView.setColorFilter(color);
    }
    /**
     * 设置中间进度百分比文字的尺寸
     *
     * @param size
     */
    public void setTextSize(float size) {
        mRateText.setTextSize(size);
    }

    /**
     * 设置中间进度百分比文字的颜色
     *
     * @param color
     */
    public void setTextColor(int color) {
        mRateText.setTextColor(color);
    }

    @Override
    public void onChange(int duration, int progress, float rate) {
        mRateText.setText(String.valueOf((int) (rate * 100) + "%"));
    }


    public ImageView getImageView() {
        return mImageView;
    }

    public void setImageView(ImageView imageView) {
        this.mImageView = imageView;
        invalidate();
    }
}
