package com.github.multiplestatusview;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

/**
 * @author: houtrry
 * @date: 2020/5/9 16:37
 * @version: $
 * @description:
 */
public class MultipleStatusLinearLayout extends LinearLayout implements OnGenerateStatusViewCallback, IMultipleStatusView {

    private MultipleStatusDelegate mMultipleStatusDelegate;

    public MultipleStatusLinearLayout(Context context) {
        this(context, null);
    }

    public MultipleStatusLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public MultipleStatusLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mMultipleStatusDelegate = new MultipleStatusDelegate();
        mMultipleStatusDelegate.init(context, attrs, this);
        mMultipleStatusDelegate.setOnGenerateStatusViewCallback(this);
    }

    @Override
    public View onAddChildView(int layoutId, int topGap) {
        View view = LayoutInflater.from(getContext()).inflate(layoutId, this, false);
        MarginLayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.topMargin = topGap;
        addView(view, layoutParams);
        System.out.println("onAddChildView:::view->" + view);
        return view;
    }

    @Override
    public void showDataView() {
        if (mMultipleStatusDelegate != null) {
            mMultipleStatusDelegate.showDataView();
        }
    }

    @Override
    public void showLoadingView() {
        if (mMultipleStatusDelegate != null) {
            mMultipleStatusDelegate.showLoadingView();
        }
    }

    @Override
    public void showLoadingView(boolean remainDataProgress) {
        if (mMultipleStatusDelegate != null) {
            mMultipleStatusDelegate.showLoadingView(remainDataProgress);
        }
    }

    @Override
    public void showErrorView() {
        if (mMultipleStatusDelegate != null) {
            mMultipleStatusDelegate.showErrorView();
        }
    }

    @Override
    public void showEmptyView() {
        if (mMultipleStatusDelegate != null) {
            mMultipleStatusDelegate.showEmptyView();
        }
    }

    @Override
    public void showNotLoginView() {
        if (mMultipleStatusDelegate != null) {
            mMultipleStatusDelegate.showNotLoginView();
        }
    }

    @Override
    public void setOnErrorViewClickListener (int viewId, @NonNull OnClickListener listener) {
        if (mMultipleStatusDelegate != null) {
            mMultipleStatusDelegate.setOnErrorViewClickListener(viewId, listener);
        }
    }

    @Override
    public void setOnErrorViewClickListener(@NonNull OnClickListener listener, int... viewIds) {
        if (mMultipleStatusDelegate != null) {
            mMultipleStatusDelegate.setOnErrorViewClickListener(listener, viewIds);
        }
    }

    @Override
    public void setOnEmptyViewClickListener (int viewId, @NonNull View.OnClickListener listener) {
        if (mMultipleStatusDelegate != null) {
            mMultipleStatusDelegate.setOnEmptyViewClickListener(viewId, listener);
        }
    }

    @Override
    public void setOnEmptyViewClickListener(@NonNull OnClickListener listener, int... viewIds) {
        if (mMultipleStatusDelegate != null) {
            mMultipleStatusDelegate.setOnEmptyViewClickListener(listener, viewIds);
        }
    }

    @Override
    public void setOnNotLoginViewClickListener (int viewId, @NonNull View.OnClickListener listener) {
        if (mMultipleStatusDelegate != null) {
            mMultipleStatusDelegate.setOnNotLoginViewClickListener(viewId, listener);
        }
    }

    @Override
    public void setOnNotLoginViewClickListener(@NonNull OnClickListener listener, int... viewIds) {
        if (mMultipleStatusDelegate != null) {
            mMultipleStatusDelegate.setOnNotLoginViewClickListener(listener, viewIds);
        }
    }
}
