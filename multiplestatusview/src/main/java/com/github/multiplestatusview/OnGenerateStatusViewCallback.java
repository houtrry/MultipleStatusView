package com.github.multiplestatusview;

import android.view.View;

import androidx.annotation.LayoutRes;

/**
 * @author: houtrry
 * @date: 2020/5/9 14:45
 * @version: $
 * @description:
 */
public interface OnGenerateStatusViewCallback {
    /**
     * 创建子View
     *
     * @param layoutId 子View的布局
     * @param topGap   子View的topMargin(默认是0)
     * @return 被创建的子View
     */
    View onAddChildView(@LayoutRes int layoutId, int topGap);
}
