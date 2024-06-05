package com.hendisantika.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-reset-password2
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 13/04/22
 * Time: 19.39
 */
@Configuration
@EnableWebMvc
public class WebMvcConfiguration implements WebMvcConfigurer {

    //Array of possible static location inside the application classpath
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/"};

    /**
     * Main method to configure possible locations.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**") .addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }

    /**
     * Registering the application HTTP filter
     * @return
     */
    @Bean
    public FilterRegistrationBean filter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new ResetPassDemoFilter());
        bean.addUrlPatterns("/*");  // or use setUrlPatterns()

        return bean;
    }
}
