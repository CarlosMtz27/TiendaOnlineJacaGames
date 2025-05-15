package com.uacm.pixelpalace;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceWebConfiguration implements WebMvcConfigurer{
	@Override
	/*public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("file:images/");
	}*/
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Imágenes estáticas (CSS, JS, Imágenes predeterminadas)
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/");
	}
}
