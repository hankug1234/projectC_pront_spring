package DTO;

import lombok.Data;

@Data
public class Video {
	private int id;
	private String videoName;
	private int fps;
	private int totalFrame;
	private int width;
	private int height;
	
	public Video(int id, String videoName, int fps, int totalFrame, int width, int height) {
		this.id = id;
		this.videoName = videoName;
		this.fps = fps;
		this.totalFrame = totalFrame;
		this.width = width;
		this.height = height;
	}
	
	public Video(int fps,int totalFrame,int width,int height) {
		this.fps = fps;
		this.totalFrame = totalFrame;
		this.width = width;
		this.height = height;
	}
	
	public Video(int id,String videoName) {
		this.id = id;
		this.videoName = videoName;
	}

}
