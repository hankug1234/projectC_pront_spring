package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import DTO.Video;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	
private BusinessModel bmodel;
	
	@Autowired
	public MainController(BusinessModel bmodel) {
		this.bmodel = bmodel;
	}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/signin")
	public String signin() {
		return "signin";
	}
	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@GetMapping("/repogitory")
	public String upload(Model model,HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		String clientId = (String)session.getAttribute("clientId");
		List<Video> list = bmodel.getVideoList(clientId);
		model.addAttribute("videoList", list);
		return "upload";
	}
	
	@GetMapping("/infomation")
	public String infomation() {
		return "infomation";
	}
	

}
