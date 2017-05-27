package com.example.musicservice;

import cn.edu.bztc.happyidion.activity.R;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;


public class AndioService extends Service{

	
	private MediaPlayer mediaPlayer;
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		mediaPlayer=MediaPlayer.create(this,R.raw.mountainwater);
		mediaPlayer.setLooping(true);//是否循环播放
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer arg0) {
				stopSelf();
			}
		});
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (!mediaPlayer.isPlaying()) {
			new Thread(){    //子线程
				@Override
				public void run() {
					super.run();
					mediaPlayer.start();
				}
			}.start();
		}
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
		}
		mediaPlayer.release();		
	}
}
