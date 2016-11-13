package code;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;

public class MyBgm extends GameObject {
	
	private MediaPlayer mBgm;
	private Context context;
	
	public MyBgm(Context context) {
		super(context);
		// TODO 自動生成されたコンストラクター・スタブ
		this.mBgm = MediaPlayer.create(context, music.bacteria.mp3);
		this.mBgm.setLooping(true);
		this.mBgm.setVolume(1.0f, 1.0f);
	}

	@Override
	public Type getType() {}
	
	@Override
	public void draw (Graphics g) {	}
	
	public void start(){
		if ( !mBgm.isPlaying() ) {
			mBgm.seekTo(0);
			mBgm.start();
		}
	}
	
	public void stop(){
		if ( mBgm.isPlaying() ) {
			mBgm.stop();
			mBgm.prepareAsync();
		}
	}
	
}