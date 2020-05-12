package com.github.multiplestatusview;

import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

/**
 * @author: houtrry
 * @date: 2020/5/12 10:56
 * @version: $
 * @description:
 */
public class MultipleStatusBean {
    private int type;
    private int layoutId;
    private View view;
    private ViewGroup parent;
    private int[] clickViewIds;
    private View.OnClickListener clickListener;
    private String viewTag;
    private boolean remainDataView;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public ViewGroup getParent() {
        return parent;
    }

    public void setParent(ViewGroup parent) {
        this.parent = parent;
    }

    public int[] getClickViewIds() {
        return clickViewIds;
    }

    public void setClickViewIds(int[] clickViewIds) {
        this.clickViewIds = clickViewIds;
    }

    public View.OnClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(View.OnClickListener listener) {
        this.clickListener = listener;
    }

    public String getViewTag() {
        return viewTag;
    }

    public void setViewTag(String viewTag) {
        this.viewTag = viewTag;
    }

    public boolean isRemainDataView() {
        return remainDataView;
    }

    public void setRemainDataView(boolean remainDataView) {
        this.remainDataView = remainDataView;
    }

    @Override
    public String toString() {
        return "MultipleStatusBean{" +
                "type=" + type +
                ", layoutId=" + layoutId +
                ", view=" + view +
                ", parent=" + parent +
                ", clickViewIds=" + Arrays.toString(clickViewIds) +
                ", clickListener=" + clickListener +
                ", viewTag='" + viewTag + '\'' +
                ", remainDataView=" + remainDataView +
                '}';
    }
}
