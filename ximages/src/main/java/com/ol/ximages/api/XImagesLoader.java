package com.ol.ximages.api;

import android.content.Context;

import com.ol.ximages.core.XImageActionBase;
import com.ol.ximages.core.XImageConfig;
import com.ol.ximages.sdk.glide.GlideImageAction;
import com.ol.ximages.utils.PreconditionsUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 接入接口
 */
public class XImagesLoader {
    //装载SingletonExtend实例的容器
    private static final Map<String,XImagesLoader> container = new HashMap<String, XImagesLoader>();
    //XImagesLoader类最多拥有的实例数量
    private static final int MAX_NUM = 3;
    //实例容器中元素的key的开始值
    private static String CACHE_KEY_PRE = "cache";
    private static int initNumber = 1;

    private static XImagesLoader INSTANCE;
    private static HashMap<Integer, XImageActionBase> MAP = new HashMap<Integer, XImageActionBase>();
    private int curImageAction = XImageConfig.IMAGE_GLIDE;

    static {
        MAP.put(XImageConfig.IMAGE_GLIDE, new GlideImageAction());
    }

    private XImagesLoader() {
    }

    public static XImagesLoader getInstance() {
        String key = CACHE_KEY_PRE+ initNumber;
        XImagesLoader singletonExtend = container.get(key);
        if (singletonExtend == null) {
            singletonExtend = new XImagesLoader();
            container.put(key,singletonExtend);
        }
        initNumber++;
        //控制容器中实例的数量
        if (initNumber > MAX_NUM) {
            initNumber = 1;
        }
        return INSTANCE;
    }



    /**
     * Sets cur image action.
     *
     * @param curImageAction the cur image action
     */
    public void setCurImageAction(int curImageAction) {
        this.curImageAction = curImageAction;
    }

    /**
     * Load image.
     *
     * @param context the context
     * @param img     the img
     */
    public void loadImage(Context context, XImageView img) {
        PreconditionsUtils.checkNotNull(context, "context is null");
        PreconditionsUtils.checkNotNull(img, "img  is null");
        if (MAP.containsKey(curImageAction)) {
            MAP.get(curImageAction).loadImage(context, img);
        }
    }

    /**
     * Load image with thumbnail.
     *
     * @param context the context
     * @param img     the img
     */
    public void loadImageWithThumbnail(Context context, XImageView img) {
        PreconditionsUtils.checkNotNull(context, "context is null");
        PreconditionsUtils.checkNotNull(img, "img  is null");
        PreconditionsUtils.checkNotNull(img.getThumbnailOption(), "img.getThumbnailOption()  is null");
        if (MAP.containsKey(curImageAction)) {
            MAP.get(curImageAction).loadImageWithThumbnail(context, img);
        }
    }

    /**
     * Load image size.
     *
     * @param context the context
     * @param img     the img
     */
    public void loadImageSize(Context context, XImageView img) {
        PreconditionsUtils.checkNotNull(context, "context is null");
        PreconditionsUtils.checkNotNull(img, "img  is null");
        PreconditionsUtils.checkNotNull(img.getImageSize(), "img.getImageSize()  is null");
//        if (context == null || img == null || img.getImageSize() == null) {
//            throw new IllegalArgumentException("context or img or imageSize is null");
//        }
        if (MAP.containsKey(curImageAction)) {
            MAP.get(curImageAction).loadImageWithSize(context, img);
        }
    }

    /**
     * Load image listener.
     *
     * @param context  the context
     * @param img      the img
     * @param listener the listener
     */
    public void loadImageListener(Context context, XImageView img, XImageLoadListener listener) {
        PreconditionsUtils.checkNotNull(context, "context is null");
        PreconditionsUtils.checkNotNull(img, "img  is null");
        if (MAP.containsKey(curImageAction)) {
            MAP.get(curImageAction).loadImageListener(context, img, listener);
        }
    }
}
