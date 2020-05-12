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

    interface ViewType {
        int Error = 0;
        int NotLogin = 1;
        int Empty = 2;
        int Loading = 3;
        int Other = 4;
    }

    /**
     * 设置指定type的子View的点击事件
     *
     * @param type     view的type类型
     * @param listener 监听器
     * @param viewIds  需要监听的子View的id
     */
    void setOnTypeViewClickListener(int type, @NonNull View.OnClickListener listener, int... viewIds);

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

    /**
     * 添加自定义类型的页面.
     * 默认隐藏数据页.
     * 注意: type 0~3 分别是错误页/未登录页/空数据页/loading页,
     * 如果type为这几个, 会覆盖默认. 慎用.
     *
     * @param type     自定义的type标识. type相同的话, 新的会覆盖旧的.
     * @param layoutId type对应的layout
     */
    void addCustomTypeView(int type, int layoutId);

    /**
     * 添加自定义类型的页面
     * 注意: type 0~3 分别是错误页/未登录页/空数据页/loading页,
     * 如果type为这几个, 会覆盖默认. 慎用.
     *
     * @param type           自定义的type标识. type相同的话, 新的会覆盖旧的.
     * @param layoutId       type对应的layout
     * @param remainDataView 显示该View的时候, 是否隐藏数据页(一般情况下, 只有loading页可能会需要同时显示数据页)
     */
    void addCustomTypeView(int type, int layoutId, boolean remainDataView);

    /**
     * 显示对应type的view.
     * 注意: 必须先add这种type的View, 也就是先调用addCustomTypeView方法
     *
     * @param type
     */
    void showCustomTypeView(int type);

    /**
     * 同时调用addCustomTypeView和showCustomTypeView
     *
     * @param type
     * @param layoutId
     */
    void showWithAddCustomTypeView(int type, int layoutId);

    /**
     * 获取指定type对应的View
     *
     * @param type
     * @return
     */
    View getTypeView(int type);
}
