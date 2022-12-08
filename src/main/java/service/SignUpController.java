package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DTO.Client;




@Controller
@RequestMapping("/register")
public class SignUpController {
	
	private BusinessModel bmodel;
	
	@Autowired
	public SignUpController(BusinessModel bmodel) {
		this.bmodel = bmodel;
	}
	

	@PostMapping
	public String processRegistration(Client client) {
		String id = client.getId();
		String pw = client.getPw();
		String repw = client.getRepw();
		
		if(pw.equals(repw)) {
			if(bmodel.getDuplicatedResult(id)) {
				return "signup";
			}else {
				bmodel.postClientInfo(id,pw);
				return "home";
			}
		}
				
		return "signup";
	}
}
