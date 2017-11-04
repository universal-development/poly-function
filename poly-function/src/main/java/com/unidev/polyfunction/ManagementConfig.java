package com.unidev.polyfunction;

import org.jminix.console.servlet.MiniConsoleServlet;
import org.springframework.boot.actuate.autoconfigure.ManagementContextConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Management configuration installation.
 */
@ManagementContextConfiguration
public class ManagementConfig {

    @Bean
    ServletRegistrationBean jmxServletRegistration() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new MiniConsoleServlet());
        servletRegistrationBean.addUrlMappings("/jmx/*");
        return servletRegistrationBean;
    }
}
