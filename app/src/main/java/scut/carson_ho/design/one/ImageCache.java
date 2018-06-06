package scut.carson_ho.design.one;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * Created by JayHan on 2018/6/5.
 */

public class ImageCache {
    //缓存LRU缓存
    LruCache<String, Bitmap> mImageCache;

    public ImageCache() {
        initImageCache();
    }

    private void initImageCache() {
        //计算可使用的最大内存
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 4;
        mImageCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight() / 1024;
            }
        };
    }

    public void put(String url, Bitmap bitmap) {
        mImageCache.put(url, bitmap);
    }

    public void get(String url) {
        mImageCache.get(url);
    }
}
