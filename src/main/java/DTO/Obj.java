package DTO;

import lombok.Data;

@Data
public class Obj {
	private int id;
	private String className;
	private String classColor;
	private int startFrame;
	private int endFrame;
	private double prob;
	private int videoId;
	
	public Obj(int id,String className,String classColor,int startFrame,int endFrame, double prob, int videoId) {
		this.id = id;
		this.className = className;
		this.classColor = classColor;
		this.startFrame = startFrame;
		this.endFrame = endFrame;
		this.prob = prob;
		this.videoId = videoId;
				
	}
	
	public Obj(int id,String className,String classColor,int startFrame,int endFrame) {
		this.id = id;
		this.className = className;
		this.classColor = classColor;
		this.startFrame = startFrame;
		this.endFrame = endFrame;
	}

}
