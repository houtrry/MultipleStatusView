package com.github.multiplestatusview;

import android.view.View;

import androidx.annotation.NonNull;

/**
 * @author: houtrry
 * @date: 2020/5/9 16:39
 * @version: $
 * @description:
 */
public interface IMultipleStatusView {
    void showDataView();

    void showLoadingView();

    void showLoadingView(boolean remainDataProgress);

    void showErrorView();

    void showEmptyView();

    void showNotLoginView();

    void setOnErrorViewClickListener(int viewId, @NonNull View.OnClickListener listener);

    void setOnEmptyViewClickListener(int viewId, @NonNull View.OnClickListener listener);

    void setOnNotLoginViewClickListener(int viewId, @NonNull View.OnClickListener listener);
}
