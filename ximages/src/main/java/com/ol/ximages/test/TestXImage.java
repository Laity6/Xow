package com.ol.ximages.test;

import android.content.Context;
import android.widget.ImageView;

import com.ol.ximages.api.XImageView;
import com.ol.ximages.api.XImagesLoader;
import com.ol.ximages.core.option.SizeOption;

public class TestXImage {

    /**
     * Load image.
     *
     * @param context the context
     * @param image   the image
     * @param url     the url
     */
    void loadImage(Context context, ImageView image, String url) {
        XImageView imageView = new XImageView.Builder().url(url).imageView(image).build();
        XImagesLoader.getInstance().loadImage(context, imageView);
    }

    /**
     * Load image.
     *
     * @param context       the context
     * @param image         the image
     * @param resDrawableId the res drawable id
     */
    void loadImage(Context context, ImageView image, int resDrawableId) {
        XImageView imageView = new XImageView.Builder().url(resDrawableId).imageView(image).build();
        XImagesLoader.getInstance().loadImage(context, imageView);
    }

    /**
     * Load image with thumbnail.
     *
     * @param context the context
     * @param image   the image
     * @param url     the url
     */
    void loadImageWithThumbnail(Context context, ImageView image, String url) {
        XImageView imageView = new XImageView.Builder().url(url).imageView(image).imageSize(new SizeOption(100, 100)).build();
        XImagesLoader.getInstance().loadImageSize(context, imageView);
    }

    /**
     * Load image size.
     *
     * @param context the context
     * @param image   the image
     * @param url     the url
     */
    void loadImageSize(Context context, ImageView image, String url) {
        XImageView imageView = new XImageView.Builder().url(url).imageView(image).imageSize(new SizeOption(100, 100)).build();
        XImagesLoader.getInstance().loadImageSize(context, imageView);
    }
}