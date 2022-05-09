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
        gdal.SetConfigOption("AWS_SECRET_ACCESS_KEY", "m0I03C0oWxnFrRFVq2KNRcwZPSh0ffiaxpFmexnA");
        gdal.SetConfigOption("AWS_ACCESS_KEY_ID", "MCNBMBAERC2UA0E2EA4P");
        gdal.SetConfigOption("AWS_S3_ENDPOINT", "http://ceph1:7480");
        Dataset dataset = gdal.Open(rasterFilePath, gdalconstConstants.GA_ReadOnly);
        Driver driver = dataset.GetDriver();
        System.out.println("Driver: " + driver.getShortName() + "/" + driver.getLongName());
    }
}