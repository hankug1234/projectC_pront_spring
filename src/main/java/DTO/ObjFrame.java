package DTO;

import lombok.Data;

@Data
public class ObjFrame {
	private int objectId;
	private int objectFrame;
	private int videoId;
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	
	public ObjFrame(int objectId,int objectFrame,int videoId,int x1,int y1, int x2, int y2) {
		this.objectId = objectId;
		this.objectFrame = objectFrame;
		this.videoId = videoId;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}

}
