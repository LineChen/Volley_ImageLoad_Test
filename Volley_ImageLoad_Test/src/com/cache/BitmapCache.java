package com.cache;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

/**
 * �Զ���ͼƬ������
 * @author Administrator
 *
 */
public class BitmapCache implements ImageCache{

	private LruCache<String, Bitmap> mCache;
	private int max_size = 10 * 1024 * 1024;//���涨��Ϊ10M,�������ֵ�ͻ���������
	
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





















