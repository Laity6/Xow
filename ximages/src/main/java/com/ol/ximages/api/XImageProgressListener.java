package com.ol.ximages.api;
/**
 * 图片显示进度可能涉及的回调响应接口
 */
public interface XImageProgressListener {

    /**
     * On progress update.
     *
     * @param objects the objects
     */
    void onProgressUpdate(Object[] objects);
}
