
package awsbase;

public class AwsBaseClient {
    protected String awsAccessKey = "MCNBMBAERC2UA0E2EA4P";
    /**
     * Secret access key
     */
    protected String awsSecretKey = "m0I03C0oWxnFrRFVq2KNRcwZPSh0ffiaxpFmexnA";
    protected String endPoint = "https://ceph1:7480";

    public AwsBaseClient() {
    }

    public AwsBaseClient(String awsAccessKey, String awsSecretKey, String endPoint) {
        this.awsAccessKey = awsAccessKey;
        this.awsSecretKey = awsSecretKey;
        this.endPoint = endPoint;
    }

    public void setBaseClient(String awsAccessKey, String awsSecretKey, String endPoint) {
        this.awsAccessKey = awsAccessKey;
        this.awsSecretKey = awsSecretKey;
        this.endPoint = endPoint;
    }

    public AwsBaseClient withAwsAccessKey(String awsAccessKey) {
        this.awsAccessKey = awsAccessKey;
        return this;
    }

    public AwsBaseClient withAwsSecretKey(String awsSecretKey) {
        this.awsSecretKey = awsSecretKey;
        return this;
    }
}
