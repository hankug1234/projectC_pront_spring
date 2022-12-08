package service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DTO.Obj;
import DTO.Video;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/file")
public class UploadController {
	
	
private BusinessModel bmodel;
private String host = "http://127.0.0.1:8085/";
private String host1 = "http://192.168.3.117:8085";


	@Autowired
	public UploadController(BusinessModel bmodel) {
		this.bmodel = bmodel;
	}
	
	@GetMapping("/show")
	public String showVideo(@RequestParam(value = "videoId")int videoId,Model model,HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		String clientId = (String) session.getAttribute("clientId");
		
		JSONObject obj = bmodel.getObjectList(clientId, videoId);
		
		List<Obj> objectList = new LinkedList<>();
		
		try {
		JSONObject datas = (JSONObject)obj.get("datas");
		JSONObject videoInfo = (JSONObject)obj.get("info");
		int fps = (int) Math.round((double) videoInfo.get("fps"));
		int totalFrame = (int) Math.round((double) videoInfo.get("totalFrame"));
		int width = (int) Math.round((double) videoInfo.get("width"));
		int height = (int) Math.round((double) videoInfo.get("height"));
		
		Iterator iter = datas.keySet().iterator();
		while(iter.hasNext()) {
			String skey = (String)iter.next();
			int key = Integer.parseInt(skey);
			JSONObject target = (JSONObject)datas.get(skey);
			String className = (String)target.get("className");
			String classColor = (String)target.get("classColor");
			int startFrame = Integer.parseInt((String)target.get("startFrame"));
			int endFrame = Integer.parseInt((String)target.get("endFrame"));
			objectList.add(new Obj(key,className,classColor,startFrame,endFrame));
		}
		
		model.addAttribute("videoInfo", new Video(fps,totalFrame,width,height));
		model.addAttribute("datas", datas);
		model.addAttribute("objectList", objectList);
		model.addAttribute("clientId", clientId);
		model.addAttribute("videoId", videoId);
		model.addAttribute("source",host1);
		}catch(NullPointerException e) {
			JSONObject videoInfo = (JSONObject)obj.get("info");
			int fps = (int) Math.round((double) videoInfo.get("fps"));
			int totalFrame = (int) Math.round((double) videoInfo.get("totalFrame"));
			int width = (int) Math.round((double) videoInfo.get("width"));
			int height = (int) Math.round((double) videoInfo.get("height"));
			
			List<Obj> failList = new LinkedList<>();
			failList.add(new Obj(0,"please select condition!","please select condition!",0,0));
			
			model.addAttribute("videoInfo", new Video(fps,totalFrame,width,height));
			model.addAttribute("datas", "");
			model.addAttribute("objectList", failList);
			model.addAttribute("clientId", clientId);
			model.addAttribute("videoId", videoId);
			model.addAttribute("source",host1);
			
		}
		return "infomation";
	}
	

}
