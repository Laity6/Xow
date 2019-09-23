package com.ol.ximages.api;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * 图片显示进度可能涉及的回调响应接口
 */
public abstract class XImageLoadListener<T,K> {
    /**
     * 图片加载成功回调
     * @param uri       图片URL 或资源id 或 文件
     * @param view      目标载体，不传则为空
     * @param resource  返回的资源，GlideDrawable 或者 Bitmap 或者 GifDrawable
     */
    public abstract void  onLoadingComplete(T uri, ImageView view,K resource);

    /**
     * 图片加载异常返回
     * @param source    图片地址，File，资源ID
     * @param e         异常信息
     */
    public abstract void onLoadingError(T source,Exception e);

    /**
     * 加载开始(option)
     * @param source        图片来源
     * @param placeHolder   开始加载占位图
     */
    public void onLoadingStart(T source, Drawable placeHolder){

    }
}
