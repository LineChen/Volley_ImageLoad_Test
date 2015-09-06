package com.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * 自定义图片换缓存
 * @author Administrator
 *
 */
public class BitmapCache implements ImageCache{

	private LruCache<String, Bitmap> mCache;
	private int max_size = 10 * 1024 * 1024;//缓存定义为10M,超过这个值就会启动回收
	
	public BitmapCache(){
		mCache = new LruCache<String, Bitmap>(max_size){
			@Override
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}
		};
	}
	
	@Override
	public Bitmap getBitmap(String url) {
		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		mCache.put(url, bitmap);
	}
	
}





















