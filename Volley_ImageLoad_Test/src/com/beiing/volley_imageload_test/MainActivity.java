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
 * 缓存功能的简单介绍 LruCache ImageCache 加载网络图片及监听 ImageRequest ImageLoader
 * NetworkImageView(或者普通的ImageView)
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

		// ----------------ImageRequest请求方式
		// //maxWidth、maxHeight可以规定我们所需的最大长和宽,(自动压缩)如果是0，则按原图加载
		ImageRequest request = new ImageRequest(url, new Listener<Bitmap>() {
			@Override
			public void onResponse(Bitmap bitmap) {
				Iv_Image.setImageBitmap(bitmap);
				Log.i("--", "加载成功");
			}
		}, 0, 0, Config.RGB_565, new ErrorListener() {

			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i("--", "加载失败");
				// 加载错误时，定义一个默认的图片
				Iv_Image.setBackgroundResource(R.drawable.ic_launcher);
			}
		});

		MyApplication.getHttpQueues().add(request);
		// --------------------

		// -------------ImageLoader方式
//		ImageLoader loader = new ImageLoader(MyApplication.getHttpQueues(),
//				new BitmapCache());
//		ImageListener listener = ImageLoader.getImageListener(Iv_Image,
//				R.drawable.ic_launcher, R.drawable.ic_launcher);
//		//加载图片，两个参数的使用原图，三个参数的会自动压缩
//		loader.get(url, listener);
		// ------------------------
		
		//-----NetworkImageView方式
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
