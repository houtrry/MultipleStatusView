package com.github.multiplestatusview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.SparseArray;
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
    private static final String TAG_PATTERN = "MultipleStatusDelegate.*";

    private SparseArray<MultipleStatusBean> mMultipleStatusMap = new SparseArray<>();

    private interface ViewTag {
        String Error = "MultipleStatusDelegate&&&Error";
        String NotLogin = "MultipleStatusDelegate&&&NotLogin";
        String Empty = "MultipleStatusDelegate&&&Empty";
        String Loading = "MultipleStatusDelegate&&&Loading";
        String Other = "MultipleStatusDelegate&&&Other";
    }


    public MultipleStatusDelegate() {
    }

    public void init(@NonNull Context context, @NonNull AttributeSet attrs, @NonNull ViewGroup rootView) {
        this.mContext = context;
        this.mRootView = rootView;
        initAttrs(attrs);
    }

    {
        //把错误页/未登录页/空数据页/loading页作为默认类型添加进去
        addTypeView(ViewType.Error, mErrorViewLayoutId, ViewTag.Error, false);
        addTypeView(ViewType.NotLogin, mNotLoginViewLayoutId, ViewTag.NotLogin, false);
        addTypeView(ViewType.Empty, mEmptyViewLayoutId, ViewTag.Empty, false);
        addTypeView(ViewType.Loading, mLoadingViewLayoutId, ViewTag.Loading, true);
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
        setOnTypeViewClickListener(ViewType.Error, listener, viewId);
    }

    @Override
    public void setOnErrorViewClickListener(@NonNull View.OnClickListener listener, int... viewIds) {
        setOnTypeViewClickListener(ViewType.Error, listener, viewIds);
    }

    @Override
    public void setOnEmptyViewClickListener(int viewId, @NonNull View.OnClickListener listener) {
        setOnTypeViewClickListener(ViewType.Empty, listener, viewId);
    }

    @Override
    public void setOnEmptyViewClickListener(@NonNull View.OnClickListener listener, int... viewIds) {
        setOnTypeViewClickListener(ViewType.Empty, listener, viewIds);
    }

    @Override
    public void setOnNotLoginViewClickListener(int viewId, @NonNull View.OnClickListener listener) {
        setOnTypeViewClickListener(ViewType.NotLogin, listener, viewId);
    }

    @Override
    public void setOnNotLoginViewClickListener(@NonNull View.OnClickListener listener, int... viewIds) {
        setOnTypeViewClickListener(ViewType.NotLogin, listener, viewIds);
    }

    @Override
    public void setOnTypeViewClickListener(int type, @NonNull View.OnClickListener listener, int... viewIds) {
        final MultipleStatusBean multipleStatusBean = mMultipleStatusMap.get(type);
        if (multipleStatusBean == null) {
            return;
        }
        multipleStatusBean.setClickListener(listener);
        multipleStatusBean.setClickViewIds(viewIds);
        View view = multipleStatusBean.getView();
        if (view != null) {
            if (!Utils.isEmpty(viewIds)) {
                for (int i = 0; i < viewIds.length; i++) {
                    view.findViewById(viewIds[i]).setOnClickListener(listener);
                }
            }
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
        showTypeView(ViewType.Loading, remainDataProgress);
    }

    @Override
    public void showErrorView() {
        showTypeView(ViewType.Error);
    }

    @Override
    public void showEmptyView() {
        showTypeView(ViewType.Empty);
    }

    @Override
    public void showNotLoginView() {
        showTypeView(ViewType.NotLogin);
    }

    private void displayDataView(boolean show) {
        boolean isOtherChild;
        View child;
        Object tag;
        for (int i = 0; i < mRootView.getChildCount(); i++) {
            child = mRootView.getChildAt(i);
            tag = child.getTag();
            isOtherChild = (tag instanceof String) && Pattern.matches(TAG_PATTERN, (String) tag);
            System.out.println(":::tag->" + tag + ", isOtherChild: " + isOtherChild);
            System.out.println(":::tag->" + tag + ", result-> " + isOtherChild + " ^ " + show + " = " + (isOtherChild ^ show));
            child.setVisibility(show == isOtherChild ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void addCustomTypeView(int type, int layoutId) {
        addCustomTypeView(type, layoutId, false);
    }

    @Override
    public void addCustomTypeView(int type, int layoutId, boolean remainDataView) {
        addTypeView(type, layoutId, ViewTag.Other, remainDataView);
    }

    @Override
    public void showCustomTypeView(int type) {
        showTypeView(type);
    }

    @Override
    public void showWithAddCustomTypeView(int type, int layoutId) {
        addCustomTypeView(type, layoutId);
        showCustomTypeView(type);
    }

    private void addTypeView(int type, int layoutId, String viewTag, boolean remainDataView) {
        //如果之前已经添加过这种type的view, 则先移除
        final MultipleStatusBean cacheMultipleStatusBean = mMultipleStatusMap.get(type);
        if (cacheMultipleStatusBean != null) {
            final View view = cacheMultipleStatusBean.getView();
            if (view != null && mRootView != null) {
                mRootView.removeView(view);
            }
        }
        //再覆盖
        final MultipleStatusBean multipleStatusBean = new MultipleStatusBean();
        multipleStatusBean.setType(type);
        multipleStatusBean.setLayoutId(layoutId);
        multipleStatusBean.setViewTag(viewTag);
        multipleStatusBean.setRemainDataView(remainDataView);
        mMultipleStatusMap.put(type, multipleStatusBean);
    }

    private void showTypeView(int type) {
        final MultipleStatusBean multipleStatusBean = mMultipleStatusMap.get(type);
        if (multipleStatusBean == null) {
            return;
        }
        showTypeViewContent(multipleStatusBean.isRemainDataView(), multipleStatusBean);
    }

    private void showTypeView(int type, Boolean remainDataView) {
        final MultipleStatusBean multipleStatusBean = mMultipleStatusMap.get(type);
        if (multipleStatusBean == null) {
            return;
        }
        showTypeViewContent(remainDataView, multipleStatusBean);
    }

    private void showTypeViewContent(boolean remainDataView, @NonNull MultipleStatusBean multipleStatusBean) {
        hideTypeViews(remainDataView);
        View view = multipleStatusBean.getView();
        if (view == null) {
            view = mOnGenerateStatusViewCallback.onAddChildView(multipleStatusBean.getLayoutId(), mTopGap);
            view.setTag(multipleStatusBean.getViewTag());
            final View.OnClickListener listener = multipleStatusBean.getClickListener();
            final int[] clickViewIds = multipleStatusBean.getClickViewIds();
            if (listener != null || !Utils.isEmpty(clickViewIds)) {
                for (int i = 0; i < clickViewIds.length; i++) {
                    view.findViewById(clickViewIds[i]).setOnClickListener(listener);
                }
            }
            multipleStatusBean.setParent(mRootView);
            multipleStatusBean.setView(view);
        }
        view.setVisibility(View.VISIBLE);
    }


    private void hideTypeViews(boolean remainDataView) {
        if (!remainDataView) {
            displayDataView(false);
        }
        MultipleStatusBean multipleStatusBean;
        View view;
        for (int i = 0; i < mMultipleStatusMap.size(); i++) {
            multipleStatusBean = mMultipleStatusMap.valueAt(i);
            if (multipleStatusBean == null) {
                continue;
            }
            System.out.println("===>>>hideTypeViews, multipleStatusBean: " + multipleStatusBean);
            view = multipleStatusBean.getView();
            if (view == null) {
                continue;
            }
            System.out.println("===>>>hideTypeViews, view: " + view);
            view.setVisibility(View.GONE);
        }
    }

    @Override
    public View getTypeView(int type) {
        final MultipleStatusBean multipleStatusBean = mMultipleStatusMap.get(type);
        return multipleStatusBean == null ? null : multipleStatusBean.getView();
    }
}
