# MultipleStatusView
Android page多状态切换

已支持 ConstraintLayout/FrameLayout/LinearLayout/RelativeLayout四中常用ViewGroup.

使用方式如下
```
<?xml version="1.0" encoding="utf-8"?>
<com.github.multiplestatusview.MultipleStatusRelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    app:errorLayout="@layout/layout_error"
    app:emptyLayout="@layout/layout_empty"
    app:notLoginLayout="@layout/layout_not_login"
    app:loadingLayout="@layout/layout_loading"
    android:id="@+id/multipleStatusView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".RelativeLayoutActivity">

    <TextView
        android:id="@+id/tvData"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/colorPrimary"
        android:gravity="center"
        android:text="relative layout"
        android:textAllCaps="false"
        android:textColor="@color/colorAccent"
        android:textSize="25sp" />

</com.github.multiplestatusview.MultipleStatusRelativeLayout>
```
```
multipleStatusView.showLoadingView(true)
```