package com.ol.ximages.core.option;

public class ThumbnailOption {

    // 缩略图资源
    private String url = null;

    /**
     * Instantiates a new Thumbnail option.
     */
    public ThumbnailOption() {
    }

    /**
     * Instantiates a new Thumbnail option.
     *
     * @param url the url
     */
    public ThumbnailOption(String url) {
        this.url = url;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

}