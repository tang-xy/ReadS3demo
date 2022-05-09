import  awsbase.*;
import  org.gdal.gdal.*;
import org.gdal.gdalconst.gdalconstConstants;

public class Main {
    public static void main(String[] args) {
        System.out.println("test");
        AwsS3Bucket bucket = new AwsS3Bucket();
        bucket.createBucketWithParams("testbucket");
        bucket.listBuckets();
        bucket.uploadFile("testkey","/zhaolong/data/Landsat8/clip/LC82015122103026741006,03310.tif");
        gdalReadS3("/vsis3/testbucket/testkey");

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