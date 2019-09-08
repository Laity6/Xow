package com.ol.xow.base.permission;

public abstract class ResultFunc {
    protected abstract void call(int requestCode, String permissions[], int[] grantResults);
}