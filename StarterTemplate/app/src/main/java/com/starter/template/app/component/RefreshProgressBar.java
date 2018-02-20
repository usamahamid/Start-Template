package com.starter.template.app.component;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import com.starter.template.R;


/**
 * A custom horizontal indeterminate progress bar which displays a smooth colored animation.
 *
 * @author Christophe Beyls
 */
public class RefreshProgressBar extends View {

    private static final float PROGRESS_BAR_HEIGHT = 2f;

    private final Handler handler;
    private boolean mIsRefreshing = false;
    private SwipeProgressBar mProgressBar;
    private final int mProgressBarHeight;

    public RefreshProgressBar(Context context) {
        this(context, null, 0);
    }

    public RefreshProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshProgressBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        handler = new Handler();
        if (!isInEditMode()) {
            mProgressBar = new SwipeProgressBar(this);
        }
        final DisplayMetrics metrics = getResources().getDisplayMetrics();
        mProgressBarHeight = (int) (metrics.density * PROGRESS_BAR_HEIGHT + 0.5f);

        //set customized color to progress bar.
        setColorSchemeResources(
                R.color.progress_1,
                R.color.progress_2,
                R.color.progress_3,
                R.color.progress_4
        );
    }

    /**
     * Starts or tops the refresh animation. Animation is stopped by default. After the stop
     * animation has completed, the progress bar will be fully transparent.
     */
    public void setRefreshing(boolean refreshing) {
        if (mIsRefreshing != refreshing) {
            mIsRefreshing = refreshing;
            handler.removeCallbacks(mRefreshUpdateRunnable);
            handler.post(mRefreshUpdateRunnable);
        }
    }

    private final Runnable mRefreshUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            if (mIsRefreshing) {
                mProgressBar.start();
            } else {
                mProgressBar.stop();
            }
        }
    };

    @Override
    protected void onDetachedFromWindow() {
        handler.removeCallbacks(mRefreshUpdateRunnable);
        super.onDetachedFromWindow();
    }

    /**
     * Set the four colors used in the progress animation from color resources.
     */
    public void setColorSchemeResources(int colorRes1, int colorRes2, int colorRes3, int
            colorRes4) {
        final Resources res = getResources();
        setColorSchemeColors(res.getColor(colorRes1), res.getColor(colorRes2),
                res.getColor(colorRes3), res.getColor(colorRes4));
    }

    /**
     * Set the four colors used in the progress animation.
     */
    public void setColorSchemeColors(int color1, int color2, int color3, int color4) {
        mProgressBar.setColorScheme(color1, color2, color3, color4);
    }

    /**
     * @return Whether the progress bar is actively showing refresh progress.
     */
    public boolean isRefreshing() {
        return mIsRefreshing;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        super.draw(canvas);
        if (mProgressBar != null) {
            mProgressBar.draw(canvas);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (mProgressBar != null) {
            mProgressBar.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),
                mProgressBarHeight);
    }
}
