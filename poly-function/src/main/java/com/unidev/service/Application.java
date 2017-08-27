package com.unidev.service;

import com.unidev.platform.j2ee.common.WebUtils;
import org.jminix.console.servlet.MiniConsoleServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@EnableDiscoveryClient
public class Application implements ServletContextInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public WebUtils webUtils() {
		return new WebUtils();
	}

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addServlet("JmxMiniConsoleServlet", MiniConsoleServlet.class).addMapping("/jmx/*");
    }

}

