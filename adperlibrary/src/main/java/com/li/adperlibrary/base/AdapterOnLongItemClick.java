package com.li.adperlibrary.base;

import android.view.View;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020/6/8 09
 * 描述：activity内实现长按点击事件
 */
public interface AdapterOnLongItemClick<T> {
    void click(View view, T t, int type, int pos);
}
