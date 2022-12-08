package service;

import java.net.URI;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import DTO.Video;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


public class BusinessModel {
	
	private String host = "http://127.0.0.1:8085";
	private String host1 = "http://192.168.3.117:8085";
	
	public boolean isLogined(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session == null) {
			return false;
		}
		return true;
		
	}
	
	public void getSession(String clientId,HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("clientId", clientId);
	}
	
	public void logOut(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
	}
	
	
	public JSONObject postApi(URI uri,MultiValueMap body) throws ParseException {
		JSONObject json = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		RestTemplate restTemplete = new RestTemplate();
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
		
		ResponseEntity<String> result = restTemplete.postForEntity(uri, request, String.class);
		System.out.println(result.getStatusCode());
        
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(result.getBody());
        json = (JSONObject) obj;
        return json;

	}
	
	
	public JSONObject getApi(URI uri) throws ParseException {
		JSONObject json = null;
		RestTemplate restTemplete = new RestTemplate();

        ResponseEntity<String> result = restTemplete.getForEntity(uri, String.class);
        System.out.println(result.getStatusCode());
        
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(result.getBody());
        json = (JSONObject) obj;
        return json;
	}
	
	public boolean postClientInfo(String id,String pw) {
		URI uri = UriComponentsBuilder
                .fromUriString(host1) //http://localhost에 호출
                .path("/signUp/")
                .encode()
                .build()
                .toUri();
		
		MultiValueMap<String,String> body = new LinkedMultiValueMap<>();
		body.add("clientId", id);
		body.add("clientPw", pw);
		
		try {
			JSONObject jsonObj =this.postApi(uri, body);
			return true;
		}catch(ParseException e){
			e.printStackTrace();
		}
		return false;
		
	}
	
	public boolean canLogin(String id,String pw) {
		
		URI uri = UriComponentsBuilder
                .fromUriString(host1) //http://localhost에 호출
                .path("/signIn/")
                .encode()
                .build()
                .toUri();
		
		MultiValueMap<String,String> body = new LinkedMultiValueMap<>();
		body.add("clientId", id);
		body.add("clientPw", pw);
		
		try {
			JSONObject jsonObj =this.postApi(uri, body);
			return Boolean.parseBoolean(jsonObj.get("result").toString());
		}catch(ParseException e){
			e.printStackTrace();
		}
		return false;
		
	}
	
	public JSONObject getObjectList(String clientId,int videoId) {
		URI uri = UriComponentsBuilder
        .fromUriString(host) //http://localhost에 호출
        .path("/getObjectsInfo/"+clientId+"/"+videoId+"/")
        .encode()
        .build()
        .toUri();
		
		 try {
		        JSONObject jsonObj = this.getApi(uri);
		        return jsonObj;
		        
		     }
		     catch(ParseException e) {
		        e.printStackTrace();
		       }
		 return null;
		
	}
	
	
	public List<Video> getVideoList(String clientId) {
		List<Video> list = new LinkedList<>();
		URI uri = UriComponentsBuilder
                .fromUriString(host) //http://localhost에 호출
                .path("/videoList/"+clientId)
                .encode()
                .build()
                .toUri();
		
		 try {
		        JSONObject jsonObj = this.getApi(uri);
		        Iterator iter = jsonObj.keySet().iterator();
		        while(iter.hasNext()) {
		        	String key = (String)iter.next();
		        	JSONObject obj = (JSONObject)jsonObj.get(key);
		        	list.add(new Video(Integer.parseInt(key),(String)obj.get("videoName")));
		        }
		        
		     }
		     catch(ParseException e) {
		        e.printStackTrace();
		     }
		     return list;
		
	}
	
	
	public boolean getDuplicatedResult(String clientId) {
		
		URI uri = UriComponentsBuilder
                .fromUriString(host) //http://localhost에 호출
                .path("/signUp/")
                .queryParam("clientId", clientId)  // query parameter가 필요한 경우 이와 같이 사용
                .encode()
                .build()
                .toUri();
		
        try {
        JSONObject jsonObj = this.getApi(uri);
        
        return Boolean.parseBoolean(jsonObj.get("result").toString());
        }
        catch(ParseException e) {
        	e.printStackTrace();
        }
        return false;

		
	}
	
}
