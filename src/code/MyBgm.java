package code;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javazoom.jl.decoder.JavaLayerException;

public class MyBgm extends GameObject {
	
	private MediaPlayer mBgm;
	
	public MyBgm(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO 自動生成されたコンストラクター・スタブ
		Playback()
	}

	@Override
	public Type getType() {
		return Type.Field;
	}
	
	@Override
	public void draw (Graphics g) {

	}
	
	public Playback (Context context) {
		this.mBgm = MediaPlayer.create(context, R.raw. );
		this.mBgm.setLooping(true);
		tshi.mBgm.setVolume(1.0f, 1.0f);
	}
	
}