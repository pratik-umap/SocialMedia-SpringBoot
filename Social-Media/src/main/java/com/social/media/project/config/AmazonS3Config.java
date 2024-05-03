package com.social.media.project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonS3Config {

	@Value("${aws.access.key}")
	private String accessKey;
	
	@Value("${aws.secret.key}")
	private String secretKey;
	
	@Bean
	public AmazonS3 amazonS3() {
		BasicAWSCredentials awsCredentials= new BasicAWSCredentials(accessKey, secretKey);
		AmazonS3 amazonS3 = AmazonS3ClientBuilder
		.standard()
		.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
	    .withRegion(Regions.AP_SOUTH_1)
	    .build();
		
		return amazonS3;
	}
	
}
