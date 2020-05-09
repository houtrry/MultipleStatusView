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
    View onAddChildView(@LayoutRes int layoutId, int topGap);
}
