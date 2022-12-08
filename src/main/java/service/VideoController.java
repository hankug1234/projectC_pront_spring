package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/video")
public class VideoController {
	
private BusinessModel bmodel;
	
	@Autowired
	public VideoController(BusinessModel bmodel) {
		this.bmodel = bmodel;
	}

}
