package com.beiing.volley_imageload_test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.cache.BitmapCache;
import com.myapplication.MyApplication;

/**
 * ���湦�ܵļ򵥽��� LruCache ImageCache ��������ͼƬ������ ImageRequest ImageLoader
 * NetworkImageView(������ͨ��ImageView)
 * 
 */
public class MainActivity extends Activity {

	private ImageView Iv_Image;
	
	private NetworkImageView Niv_Image;

	String url = "http://www.baidu.com/img/bd_logo.png";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

		// ----------------ImageRequest����ʽ
		// //maxWidth��maxHeight���Թ涨�����������󳤺Ϳ�,(�Զ�ѹ��)�����0����ԭͼ����
		ImageRequest request = new ImageRequest(url, new Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap bitmap) {
				Iv_Image.setImageBitmap(bitmap);
				Log.i("--", "���سɹ�");
			}
		}, 0, 0, Config.RGB_565, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i("--", "����ʧ��");
				// ���ش���ʱ������һ��Ĭ�ϵ�ͼƬ
				Iv_Image.setBackgroundResource(R.drawable.ic_launcher);
			}
		});

		MyApplication.getHttpQueues().add(request);
		// --------------------

		// -------------ImageLoader��ʽ
//		ImageLoader loader = new ImageLoader(MyApplication.getHttpQueues(),
//				new BitmapCache());
//		ImageListener listener = ImageLoader.getImageListener(Iv_Image,
//				R.drawable.ic_launcher, R.drawable.ic_launcher);
//		//����ͼƬ������������ʹ��ԭͼ�����������Ļ��Զ�ѹ��
//		loader.get(url, listener);
		// ------------------------
		
		//-----NetworkImageView��ʽ
		ImageLoader loader = new ImageLoader(MyApplication.getHttpQueues(),
				new BitmapCache());
		
		Niv_Image.setDefaultImageResId(R.drawable.ic_launcher);
		Niv_Image.setErrorImageResId(R.drawable.ic_launcher);
		Niv_Image.setImageUrl(url, loader);
		
	}

	public void initView() {
		Iv_Image = (ImageView) findViewById(R.id.Iv_Image);
		Niv_Image = (NetworkImageView) findViewById(R.id.Niv_Image);
	}

}
