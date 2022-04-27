import  awsbase.*;
import  org.gdal.gdal.*;
import org.gdal.gdalconst.gdalconstConstants;

public class Main {
    public static void main(String[] args) {
        System.out.println("test");
        AwsS3Bucket bucket = new AwsS3Bucket();
        bucket.createBucketBase();
        bucket.listBuckets();
        bucket.uploadFile("key","path");
        gdalReadS3("/vsis3/bucket/key");

    }

    public static void gdalReadS3(String rasterFilePath) {
        gdal.AllRegister();
        gdal.SetConfigOption("AWS_SECRET_ACCESS_KEY", "AWS_SECRET_ACCESS_KEY");
        gdal.SetConfigOption("AWS_ACCESS_KEY_ID", "AWS_ACCESS_KEY_ID");
        gdal.SetConfigOption("AWS_S3_ENDPOINT", "http://ceph01:7480");
        Dataset dataset = gdal.Open(rasterFilePath, gdalconstConstants.GA_ReadOnly);
        Driver driver = dataset.GetDriver();
        System.out.println("Driver: " + driver.getShortName() + "/" + driver.getLongName());
    }
}