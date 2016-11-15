package code;

import java.applet.Applet;
import java.applet.AudioClip;

public class GameSound {
	
	private AudioClip audio;
	private String filePath;
	
	public GameSound(String p) {
		this.filePath = p;
		this.audio = Applet.newAudioClip(getClass().getResource(filePath));	
	}
	
	public void play() {
		audio.play();
	}
	
	public void start() {
		audio.loop();
	}
	
	public void stop() {
		audio.stop();
	}
	
}
