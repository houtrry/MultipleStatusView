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
    /**
     * 展示数据页
     */
    void showDataView();

    /**
     * 展示loading(默认不会隐藏数据页)
     */
    void showLoadingView();

    /**
     * 展示loading
     *
     * @param remainDataProgress 是否隐藏数据页
     */
    void showLoadingView(boolean remainDataProgress);

    /**
     * 展示错误页
     */
    void showErrorView();

    /**
     * 展示空数据页
     */
    void showEmptyView();

    /**
     * 展示未登录页
     */
    void showNotLoginView();

    /**
     * 设置错误页面的点击事件
     *
     * @param viewId   接收点击事件的View id
     * @param listener
     */
    void setOnErrorViewClickListener(int viewId, @NonNull View.OnClickListener listener);

    /**
     * 设置错误页面的点击事件
     *
     * @param listener
     * @param viewIds  接收点击事件的View id
     */
    void setOnErrorViewClickListener(@NonNull View.OnClickListener listener, int... viewIds);

    /**
     * 设置空数据页面的点击事件
     *
     * @param viewId   接收点击事件的View id
     * @param listener
     */
    void setOnEmptyViewClickListener(int viewId, @NonNull View.OnClickListener listener);

    /**
     * 设置空数据页面的点击事件
     *
     * @param listener
     * @param viewIds  接收点击事件的View id
     */
    void setOnEmptyViewClickListener(@NonNull View.OnClickListener listener, int... viewIds);


    /**
     * 设置未登录页面的点击事件
     *
     * @param viewId   接收点击事件的View id
     * @param listener
     */
    void setOnNotLoginViewClickListener(int viewId, @NonNull View.OnClickListener listener);

    /**
     * 设置未登录页面的点击事件
     *
     * @param listener
     * @param viewIds  接收点击事件的View id
     */
    void setOnNotLoginViewClickListener(@NonNull View.OnClickListener listener, int... viewIds);

}
