package com.cmu.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.cmu")
public class HelloWorldConfiguration implements WebMvcConfigurer {

  @Bean(name = "multipartResolver")
  public StandardServletMultipartResolver resolver() {
    return new StandardServletMultipartResolver();
  }

  /**
   * Configure ViewResolvers to deliver preferred views.
   */
  @Bean
  public ViewResolver viewResolver() {
    final InternalResourceViewResolver bean = new InternalResourceViewResolver();

    bean.setViewClass(JstlView.class);
    bean.setPrefix("/WEB-INF/views/");
    bean.setSuffix(".jsp");

    return bean;
  }

  /**
   * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
   */
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**").addResourceLocations("/static/");
  }

  /**
   * Configure MessageSource to lookup any validation/error message in internationalized property files
   */
  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasename("messages");
    return messageSource;
  }

  /**
   * Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.'
   * in @PathVaidables argument. It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in
   * Spring 4.1.7. This is a workaround for this issue.
   */
  public void configurePathMatch(PathMatchConfigurer matcher) {
    matcher.setUseRegisteredSuffixPatternMatch(true);
  }

  @Bean
  public ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
    ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
    arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
    return arrayHttpMessageConverter;
  }

  private List<MediaType> getSupportedMediaTypes() {
    List<MediaType> list = new ArrayList<MediaType>();
    list.add(MediaType.IMAGE_JPEG);
    list.add(MediaType.IMAGE_PNG);
    list.add(MediaType.APPLICATION_OCTET_STREAM);
    return list;
  }

}
