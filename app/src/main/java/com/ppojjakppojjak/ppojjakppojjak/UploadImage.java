package com.ppojjakppojjak.ppojjakppojjak;

import android.graphics.Bitmap;

public class UploadImage {
    public Bitmap thumbnail;

    public Bitmap getThumbnail() {
        return this.thumbnail;
    }

    public UploadImage(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }
}
