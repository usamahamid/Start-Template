package com.starter.template.app.component;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class SupportRecycler extends RecyclerView {
    private View emptyView;
    private boolean enabled = true;
    private View loadingView;
    private View retryView;
    private boolean isLoading = false;
    private boolean isRetry = false;
    private Runnable setRefreshingRunnable = new Runnable() {
        @Override
        public void run() {
            loadingView.setVisibility(VISIBLE);
            ((SwipeRefreshLayout) loadingView).setRefreshing(true);
        }
    };
    private Runnable setStopRefreshingRunnable = new Runnable() {
        @Override
        public void run() {
            ((SwipeRefreshLayout) loadingView).setRefreshing(false);
        }
    };
    private Runnable startRetryRunnable = new Runnable() {
        @Override
        public void run() {
            ((SwipeRefreshLayout) loadingView).setRefreshing(false);
            loadingView.setVisibility(GONE);
            emptyView.setVisibility(View.GONE);
            retryView.setVisibility(View.VISIBLE);
        }
    };

    public SupportRecycler(Context context) {
        super(context);
    }

    public SupportRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SupportRecycler(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null) {
            adapter.registerAdapterDataObserver(emptyObserver);
        }
        emptyObserver.onChanged();
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }


    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public void setLoadingView(View loadingView) {
        this.loadingView = loadingView;
    }

    private AdapterDataObserver emptyObserver = new AdapterDataObserver() {

        @Override
        public void onChanged() {
            Adapter<?> adapter = getAdapter();
            if (enabled && adapter != null && emptyView != null) {
                if (isLoading && loadingView != null) {
                    setLoading();
                } else if (isRetry && retryView != null) {
                    setRetrying();
                } else {
                    setListing(adapter);
                }
            }

        }
    };

    private void setLoading() {
        emptyView.setVisibility(View.GONE);
        if (retryView != null) {
            retryView.setVisibility(View.GONE);
        }
        if (loadingView instanceof SwipeRefreshLayout) {
            loadingView.removeCallbacks(setRefreshingRunnable);
            loadingView.post(setRefreshingRunnable);
        } else {
            loadingView.setVisibility(View.VISIBLE);
            SupportRecycler.this.setVisibility(View.GONE);
        }
    }

    private void setRetrying() {
        if (loadingView instanceof SwipeRefreshLayout) {
            loadingView.removeCallbacks(startRetryRunnable);
            loadingView.post(startRetryRunnable);
        } else {
            if (loadingView != null) {
                loadingView.setVisibility(View.GONE);
            }
            emptyView.setVisibility(View.GONE);
            retryView.setVisibility(View.VISIBLE);
            SupportRecycler.this.setVisibility(View.GONE);
        }
    }

    private void setListing(Adapter<?> adapter) {
        if (loadingView != null) {
            if (loadingView instanceof SwipeRefreshLayout) {
                loadingView.removeCallbacks(setStopRefreshingRunnable);
                loadingView.post(setStopRefreshingRunnable);
            } else {
                loadingView.setVisibility(View.GONE);
            }
        }
        if (retryView != null) {
            retryView.setVisibility(View.GONE);
        }

        if (adapter.getItemCount() == 0) {
            if (loadingView instanceof SwipeRefreshLayout) {
                loadingView.setVisibility(View.GONE);
            } else {
                SupportRecycler.this.setVisibility(View.GONE);
            }
            emptyView.setVisibility(View.VISIBLE);
        } else {
            emptyView.setVisibility(View.GONE);
            if (loadingView instanceof SwipeRefreshLayout) {
                loadingView.setVisibility(View.VISIBLE);
            } else {
                SupportRecycler.this.setVisibility(View.VISIBLE);
            }
        }
    }


    public void setRetrying(boolean retry) {
        isRetry = retry;
    }

    public void setRetryView(View retryView) {
        this.retryView = retryView;
    }
}
