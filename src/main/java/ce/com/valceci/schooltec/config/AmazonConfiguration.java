package ce.com.valceci.schooltec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Configuration
public class AmazonConfiguration {
	
	private static final String ACCESS_KEY = "AKIAW2SPFDL6GFTQ5VJX";
	private static final String SECRET_KEY = "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";
	private static final String REGION = "us-east-1";
	
	@Bean
	public BasicAWSCredentials basicAWSCredentials() {
		return new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);
	}
	
	@Bean
	public AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder.standard()
				.withRegion(REGION)
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials()))
				.build();
	}
}
