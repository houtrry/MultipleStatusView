# MultipleStatusView
Android page多状态(数据页/未登录页/错误页/空数据页/loading页)切换

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
下面是 错误页/空数据页/未登录页/loading页的默认页面, 重写覆盖即可
layout/layout_error
layout/layout_empty
layout/layout_not_login
layout/layout_loading

实现逻辑:
只是对ConstraintLayout/FrameLayout/LinearLayout/RelativeLayout做功能增强, 并不影响ConstraintLayout/FrameLayout/LinearLayout/RelativeLayout的原有逻辑.
而且, 默认情况下, 不展示就不添加错误页/空数据页/未登录页/loading页, 不会影响Activity的启动性能