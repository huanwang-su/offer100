package com.yiguo.offer100.search;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication(scanBasePackages = "com.yiguo.offer100.search")
@Configuration
public class Offer100SearchApplication {

	@Value("${app.solr.job.url}")
	private String jobUrl;
	@Value("${app.solr.talent.url}")
	private String talentUrl;
	@Value("${app.solr.connectionTimeout}")
	private Integer connectionTimeout;

	@Bean("talentHttpSolrClient")
	HttpSolrClient talentHttpSolrClient(){
		HttpSolrClient ret = new HttpSolrClient(talentUrl);
		ret.setConnectionTimeout(connectionTimeout);
		ret.setAllowCompression(false);
		ret.setMaxTotalConnections(5);
		return ret;
	}

	@Bean("jobHttpSolrClient")
	HttpSolrClient jobHttpSolrClient(){
		HttpSolrClient ret = new HttpSolrClient(jobUrl);
		ret.setConnectionTimeout(connectionTimeout);
		ret.setAllowCompression(false);
		ret.setMaxTotalConnections(5);
		return ret;
	}

	public static void main(String[] args) {
		SpringApplication.run(Offer100SearchApplication.class, args);

	}
}
