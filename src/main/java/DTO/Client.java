package DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String pw;
	private String repw;
	
	public Client(String id,String pw) {
		this.id= id;
		this.pw = pw;
	}
}
