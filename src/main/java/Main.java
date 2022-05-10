import  awsbase.*;
import  org.gdal.gdal.*;
import org.gdal.gdalconst.gdalconstConstants;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        gdal.AllRegister();
        test();
        System.out.println("test");
        AwsS3Bucket bucket = new AwsS3Bucket();
        System.out.println("list");
        bucket.listBuckets();
        System.out.println("create");
        bucket.createBucketWithParams("testbucket");
        bucket.listBuckets();
        bucket.uploadFile("testkey","/zhaolong/data/Landsat8/clip/LC82015122103026741006,03310.tif");

        try{
//            byte[] imageBuffer = String.valueOf(bucket.downloadFile("testkey")).getBytes();
            gdalReadS3("testkey.tif", bucket.downloadFile("testkey"));
        } catch (IOException exception){
            System.out.println(exception);
        }

    }

    public static void test() {
        try{
            String path = "/zhaolong/data/Landsat8/clip/LC82015122103026741006,03310.tif";
            byte[] imageBuffer = toByteArray(path);
            String memFilename = "/vsimem/" + path;
            gdal.FileFromMemBuffer(memFilename, imageBuffer);
            Dataset dataset = gdal.Open(memFilename, gdalconstConstants.GA_ReadOnly);
            Driver driver = dataset.GetDriver();
            System.out.println("Driver: " + driver.getShortName() + "/" + driver.getLongName());
        } catch (IOException exception){
            System.out.println(exception);
        }


    }
    public static void gdalReadS3(String objname, byte[] imageBuffer) {
//        gdal.SetConfigOption("AWS_SECRET_ACCESS_KEY", "m0I03C0oWxnFrRFVq2KNRcwZPSh0ffiaxpFmexnA");
//        gdal.SetConfigOption("AWS_ACCESS_KEY_ID", "MCNBMBAERC2UA0E2EA4P");
//        gdal.SetConfigOption("AWS_S3_ENDPOINT", "http://10.3.102.82:7480");
//        gdal.SetConfigOption("AWS_HTTPS", "NO");
        String memFilename = "/vsimem/" + objname;
        gdal.FileFromMemBuffer(memFilename, imageBuffer);
        Dataset dataset = gdal.Open(memFilename, gdalconstConstants.GA_ReadOnly);
        Driver driver = dataset.GetDriver();
        System.out.println("Driver: " + driver.getShortName() + "/" + driver.getLongName());
    }

    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }
}