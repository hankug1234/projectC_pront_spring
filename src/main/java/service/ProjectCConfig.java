package service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProjectCConfig {
	
	@Bean
	public BusinessModel BusinessService() {
		return new BusinessModel();
	}

}
