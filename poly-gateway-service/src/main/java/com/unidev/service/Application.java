package com.unidev.service;

import com.unidev.platform.j2ee.common.WebUtils;
import org.jminix.console.servlet.MiniConsoleServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
@EnableDiscoveryClient
@EnableWebSecurity
public class Application extends WebSecurityConfigurerAdapter implements ServletContextInitializer {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "http.factory")
	public HttpComponentsClientHttpRequestFactory httpRequestFactory() {
		return new HttpComponentsClientHttpRequestFactory();
	}

	@LoadBalanced
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(httpRequestFactory());
	}

	@Bean
	public WebUtils webUtils() {
		return new WebUtils();
	}

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addServlet("JmxMiniConsoleServlet", MiniConsoleServlet.class).addMapping("/jmx/*");
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
				.authorizeRequests()
				.antMatchers("/**", "/").permitAll();
	}

	@Value("${admin.user}")
	private String adminUser;

	@Value("${admin.password}")
	private String adminPassword;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
				.inMemoryAuthentication()
				.withUser(adminUser).password(adminPassword).roles("ADMIN");
	}

}
