
package awsbase;

public class AwsBaseClient {
    protected String awsAccessKey = "ak";
    /**
     * Secret access key
     */
    protected String awsSecretKey = "sk";
    protected String endPoint = "https://ceph01:7480";

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
