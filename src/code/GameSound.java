package code;

import java.applet.AudioClip;

import javax.swing.JApplet;

public class GameSound {
	
	private AudioClip audio;
	private String filePath;
	
	public GameSound(String p) {
		this.filePath = p;
		this.audio = JApplet.newAudioClip(getClass().getResource(this.filePath));	
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
