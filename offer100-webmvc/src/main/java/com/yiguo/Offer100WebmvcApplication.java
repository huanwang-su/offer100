package com.yiguo;

import org.apache.ibatis.type.Alias;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.io.IOException;

@Configuration
@ComponentScan(basePackages = {"com.yiguo","com.yiguo.offer100.search"})
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@MapperScan("com.yiguo.dao")
public class Offer100WebmvcApplication implements Runnable{

	private String[] appArgs;
	public String[] getAppArgs() {
		return appArgs;
	}

	public void setAppArgs(String[] appArgs) {
		this.appArgs = appArgs;
	}

	// 用于处理编码问题
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

	//文件下载
	@Bean
	public HttpMessageConverters restFileDownloadSupport() {
		ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
		return new HttpMessageConverters(arrayHttpMessageConverter);
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
		PropertySourcesPlaceholderConfigurer c = new PropertySourcesPlaceholderConfigurer();
		c.setIgnoreUnresolvablePlaceholders(true);
		return c;
	}

	public static void main(String[] args) throws IOException {
		Offer100WebmvcApplication app = new Offer100WebmvcApplication();
		app.setAppArgs(args);
		app.run();
	}

	@Override
	public void run() {
		ApplicationContext context = new SpringApplicationBuilder(Offer100WebmvcApplication.class)
				.initializers()
				.run(getAppArgs());
	}
}
