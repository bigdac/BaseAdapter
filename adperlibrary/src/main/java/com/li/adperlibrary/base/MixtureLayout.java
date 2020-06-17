package com.li.adperlibrary.base;

/**
 * @author li
 * 版本：1.0
 * 创建日期：2020/6/12 15
 * 描述：实现多布局
 */
public interface MixtureLayout<T> {
    public int getLayoutId(T data,int postion);
}
