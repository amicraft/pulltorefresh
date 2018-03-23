package com.wlib.pulltorefresh;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {

    public PullToRefreshRecyclerView(Context context) {
        super(context);
    }

    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
        super(context, mode, animStyle);
    }

    /**
     * 刷新的方向
     *
     * @return
     */
    @Override
    public Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    /**
     * 创建刷新内容
     *
     * @param context Context to create view with
     * @param attrs   AttributeSet from wrapped class. Means that anything you include in the XML layout declaration
     *                will be routed to the created View
     * @return
     */
    @Override
    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
        RecyclerView recycler = new RecyclerView(context, attrs);
        recycler.setId(R.id.recyclerview);
        return recycler;
    }

    /**
     * 控件是否到底
     *
     * @return
     */

    @Override
    protected boolean isReadyForPullEnd() {
        RecyclerView recycler = getRefreshableView();//获取刷新的view
        final RecyclerView.Adapter adapter = recycler.getAdapter();
        if (null == adapter || adapter.getItemCount() == 0) {
            return true;
        } else {
            final int lastItemPosition = adapter.getItemCount() - 1;
            final int lastVisiblePosition = ((LinearLayoutManager) recycler.getLayoutManager()).
                    findLastVisibleItemPosition();
            if (lastVisiblePosition >= lastItemPosition - 1) {
                final int childIndex = lastVisiblePosition - ((LinearLayoutManager) recycler.getLayoutManager())
                        .findFirstVisibleItemPosition();
                final View lastVisibleChild = recycler.getChildAt(childIndex);
                if (lastVisibleChild != null) {
                    return lastVisibleChild.getBottom() <= recycler.getBottom();
                }
            }
        }
        return false;
    }

    @Override
    protected boolean isReadyForPullStart() {
        RecyclerView recycler = getRefreshableView();
        final RecyclerView.Adapter adapter = recycler.getAdapter();

        if (null == adapter || adapter.getItemCount() == 0) {
            return true;
        } else {

            if (((LinearLayoutManager) recycler.getLayoutManager()).findFirstVisibleItemPosition() <= 1) {
                final View firstVisibleChild = recycler.getChildAt(0);
                if (firstVisibleChild != null) {
                    return firstVisibleChild.getTop() >= recycler.getTop();
                }
            }
        }
        return false;
    }

}
