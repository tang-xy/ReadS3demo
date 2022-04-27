package awsbase;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AwsS3Client extends AwsBaseClient  {
    protected AmazonS3 s3;

    public AwsS3Client() {
        super();
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        this.s3 = AmazonS3Client.builder().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
        this.s3.setEndpoint(endPoint);
    }

    public AwsS3Client(String awsAccessKey, String awsSecretKey,String endPoint) {
        super(awsAccessKey, awsSecretKey, endPoint);
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        this.s3 = AmazonS3Client.builder().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
        this.s3.setEndpoint(endPoint);
    }

    public void setS3Client(String awsAccessKey, String awsSecretKey, String endPoint) {
        setBaseClient(awsAccessKey, awsSecretKey, endPoint);
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccessKey, awsSecretKey);
        this.s3 = AmazonS3Client.builder().withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();
        this.s3.setEndpoint(endPoint);
    }

    public AmazonS3 getS3Client() {
        return s3;
    }


}
