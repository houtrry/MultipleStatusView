package com.github.multiplestatusview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.regex.Pattern;

/**
 * @author: houtrry
 * @date: 2020/5/9 13:49
 * @version: $
 * @description:
 */
public class MultipleStatusDelegate implements IMultipleStatusView {

    private Context mContext;
    private OnGenerateStatusViewCallback mOnGenerateStatusViewCallback;
    private int mErrorViewLayoutId = R.layout.layout_error;
    private int mNotLoginViewLayoutId = R.layout.layout_not_login;
    private int mEmptyViewLayoutId = R.layout.layout_empty;
    private int mLoadingViewLayoutId = R.layout.layout_loading;
    private int mTopGap = 0;
    private ViewGroup mRootView;
    private View mErrorView;
    private View mNotLoginView;
    private View mEmptyView;
    private View mLoadingView;

    private interface ViewTag {
        String Error = "MultipleStatusDelegate$Error";
        String NotLogin = "MultipleStatusDelegate$NotLogin";
        String Empty = "MultipleStatusDelegate$Empty";
        String Loading = "MultipleStatusDelegate$Loading";
    }

    public MultipleStatusDelegate() {
    }

    public void init(@NonNull Context context, @NonNull AttributeSet attrs, @NonNull ViewGroup rootView) {
        this.mContext = context;
        this.mRootView = rootView;
        initAttrs(attrs);
    }

    public void setOnGenerateStatusViewCallback(OnGenerateStatusViewCallback mOnGenerateStatusViewCallback) {
        this.mOnGenerateStatusViewCallback = mOnGenerateStatusViewCallback;
    }

    @SuppressLint("CustomViewStyleable")
    private void initAttrs(@NonNull AttributeSet attrs) {
        final TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.MultipleStatusView);
        mErrorViewLayoutId = typedArray.getResourceId(R.styleable.MultipleStatusView_errorLayout, R.layout.layout_error);
        mNotLoginViewLayoutId = typedArray.getResourceId(R.styleable.MultipleStatusView_notLoginLayout, R.layout.layout_not_login);
        mEmptyViewLayoutId = typedArray.getResourceId(R.styleable.MultipleStatusView_emptyLayout, R.layout.layout_empty);
        mLoadingViewLayoutId = typedArray.getResourceId(R.styleable.MultipleStatusView_loadingLayout, R.layout.layout_loading);
        mTopGap = (int) (typedArray.getDimension(R.styleable.MultipleStatusView_topGap, 0) + 0.5f);
        typedArray.recycle();
    }


    @Override
    public void setOnErrorViewClickListener(int viewId, @NonNull View.OnClickListener listener) {
        if (mErrorView != null) {
            mErrorView.findViewById(viewId).setOnClickListener(listener);
        }
    }

    @Override
    public void setOnEmptyViewClickListener(int viewId, @NonNull View.OnClickListener listener) {
        if (mEmptyView != null) {
            mEmptyView.findViewById(viewId).setOnClickListener(listener);
        }
    }

    @Override
    public void setOnNotLoginViewClickListener(int viewId, @NonNull View.OnClickListener listener) {
        if (mNotLoginView != null) {
            mNotLoginView.findViewById(viewId).setOnClickListener(listener);
        }
    }

    @Override
    public void showDataView() {
        displayDataView(true);
    }

    @Override
    public void showLoadingView() {
        showLoadingView(true);
    }

    @Override
    public void showLoadingView(boolean remainDataProgress) {
        hideViews(!remainDataProgress);
        if (mLoadingView == null) {
            if (mOnGenerateStatusViewCallback == null) {
                throw new NullPointerException("mOnGenerateStatusViewCallback is null");
            }
            mLoadingView = mOnGenerateStatusViewCallback.onAddChildView(mLoadingViewLayoutId, mTopGap);
            mLoadingView.setTag(ViewTag.Loading);
        }
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorView() {
        hideViews(true);
        if (mErrorView == null) {
            if (mOnGenerateStatusViewCallback == null) {
                throw new NullPointerException("mOnGenerateStatusViewCallback is null");
            }
            mErrorView = mOnGenerateStatusViewCallback.onAddChildView(mErrorViewLayoutId, mTopGap);
            mErrorView.setTag(ViewTag.Error);
        }
        mErrorView.setVisibility(View.VISIBLE);
        System.out.println(":::" + mErrorView);
        System.out.println(":::" + mErrorView.getMeasuredWidth() + "-" + mErrorView.getMeasuredHeight());
    }

    @Override
    public void showEmptyView() {
        if (mEmptyView == null) {
            if (mOnGenerateStatusViewCallback == null) {
                throw new NullPointerException("mOnGenerateStatusViewCallback is null");
            }
            mEmptyView = mOnGenerateStatusViewCallback.onAddChildView(mEmptyViewLayoutId, mTopGap);
            mEmptyView.setTag(ViewTag.Empty);
        }
        hideViews(true);
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNotLoginView() {
        hideViews(true);
        if (mNotLoginView == null) {
            if (mOnGenerateStatusViewCallback == null) {
                throw new NullPointerException("mOnGenerateStatusViewCallback is null");
            }
            mNotLoginView = mOnGenerateStatusViewCallback.onAddChildView(mNotLoginViewLayoutId, mTopGap);
            mNotLoginView.setTag(ViewTag.NotLogin);
        }
        mNotLoginView.setVisibility(View.VISIBLE);
    }

    private void hideViews(boolean hideData) {
        if (hideData) {
            displayDataView(false);
        }
        if (mEmptyView != null) {
            mEmptyView.setVisibility(View.GONE);
        }
        if (mErrorView != null) {
            mErrorView.setVisibility(View.GONE);
        }
        if (mNotLoginView != null) {
            mNotLoginView.setVisibility(View.GONE);
        }
        if (mLoadingView != null) {
            mLoadingView.setVisibility(View.GONE);
        }
    }

    private static final String TAG_PATTERN = "MultipleStatusDelegate.*";

    private void displayDataView(boolean show) {
        boolean isOtherChild;
        View child;
        Object tag;
        for (int i = 0; i < mRootView.getChildCount(); i++) {
            child = mRootView.getChildAt(i);
            tag = child.getTag();
            isOtherChild = (tag instanceof String) && Pattern.matches(TAG_PATTERN, (String) tag);
            System.out.println(":::tag->" + tag + ", isOtherChild: " + isOtherChild);
            System.out.println(":::tag->" + tag + ", result-> " +  isOtherChild + " ^ " + show +" = "+(isOtherChild ^ show));
            child.setVisibility(show == isOtherChild ? View.GONE : View.VISIBLE);
        }
    }

}
