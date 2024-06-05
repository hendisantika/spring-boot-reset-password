package com.hendisantika;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootResetPassword2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootResetPassword2Application.class, args);
    }

//    @Bean(name = "cxfServlet")
//    public ServletRegistrationBean<CXFServlet>  cxfServlet() {
//        return new ServletRegistrationBean(new CXFServlet(), "/webservice/*");
//    }

//    @Bean
//    public DispatcherServletRegistrationBean dispatcherServletRegistrationBean() {
//        return new DispatcherServletRegistrationBean(dispatcherServlet(), "/");
//    }
//
//    @Bean
//    public DispatcherServlet dispatcherServlet() {
//        DispatcherServlet dispatcherServlet = new DispatcherServlet();
//        return dispatcherServlet;
//    }

}
