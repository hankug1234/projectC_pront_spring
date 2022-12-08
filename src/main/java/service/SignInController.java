package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DTO.Client;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/log")
public class SignInController {
	
private BusinessModel bmodel;
	
	@Autowired
	public SignInController(BusinessModel bmodel) {
		this.bmodel = bmodel;
	}
	
	@GetMapping("/in")
	public String logIn(HttpServletRequest request,HttpServletResponse response,Client client) {
		String id = client.getId();
		String pw = client.getPw();
		
		if(bmodel.isLogined(request)) {
			return "home";
		}
		
		if(bmodel.canLogin(id, pw)) {
			bmodel.getSession(id, request);
			return "home";
		}else {
			return "signin";
		}
	}
	
	@GetMapping("/out")
	public String logOut(HttpServletRequest request,HttpServletResponse response) {
		bmodel.logOut(request);
		return "home";
	}

}
